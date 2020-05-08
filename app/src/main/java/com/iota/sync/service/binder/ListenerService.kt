package com.iota.sync.service.binder

import android.app.Service
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.IBinder
import android.util.Log
import com.iota.sync.service.handler.IRequestHandler
import com.iota.sync.service.metric.network.AbstractNetworkMetric
import com.iota.sync.service.mqtt.IMQTTClient
import com.iota.sync.service.scheduler.IScheduler
import com.iota.sync.service.scheduler.jobs.RestartServiceJob
import org.koin.android.ext.android.inject
import org.koin.core.KoinComponent
import org.koin.core.inject

/**
 * Created by Adithya S. on 05/06/2020
 */

class ListenerService : Service() {

    private val scheduler: IScheduler by inject()
    private val handler: IRequestHandler by inject()
    private val networkMetric: AbstractNetworkMetric by inject()

    override fun onBind(intent: Intent?): IBinder? {
        return handler.getBinder()
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        startup()

        Log.d(TAG, "${networkMetric.getMetric()}")
        return super.onStartCommand(intent, flags, startId)
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "Notification Listening Service has been destroyed. Rescheduling.")

        scheduler.scheduleJob(10000, RestartServiceJob::class.java, 101)

        mqttClient.disconnectFromBroker()
    }

    private fun startup() {
        Log.d(TAG, "Listener service started.")

        ForegroundServiceUtil.startForeground(
    this,
            TAG,
            NOTIFICATION_CHANNEL,
            NOTIFICATION_TITLE,
            NOTIFICATION_DESC
        )

        mqttClient.connectToBroker()
    }

    companion object : KoinComponent {
        private const val TAG = "NotificationListener"

        private const val NOTIFICATION_CHANNEL = "SYNC"
        private const val NOTIFICATION_TITLE = "Syncing"
        private const val NOTIFICATION_DESC = ""

        val mqttClient: IMQTTClient by inject()

        fun start(context: Context) {
            val i = Intent(context, ListenerService::class.java)

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                context.startForegroundService(i)
            } else {
                context.startService(i)
            }
        }
    }
}