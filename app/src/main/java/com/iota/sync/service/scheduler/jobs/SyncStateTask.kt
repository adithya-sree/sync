package com.iota.sync.service.scheduler.jobs

import android.app.job.JobParameters
import android.app.job.JobService
import android.util.Log
import com.iota.sync.service.binder.ListenerService
import com.iota.sync.service.metric.IMetric
import com.iota.sync.service.metric.battery.AbstractBatteryMetric
import com.iota.sync.service.metric.location.AbstractLocationMetric
import com.iota.sync.service.metric.network.AbstractNetworkMetric
import com.iota.sync.service.scheduler.IScheduler
import org.koin.android.ext.android.inject
import kotlin.concurrent.thread

/**
 * Created by Adithya S. on 05/08/2020
 */

class SyncStateTask : JobService() {

    private val metrics: ArrayList<IMetric> = arrayListOf()

    private val scheduler: IScheduler by inject()

    init {
        metrics.apply {
            val batteryMetric: AbstractBatteryMetric by inject()
            metrics.add(batteryMetric)

            val networkMetric: AbstractNetworkMetric by inject()
            metrics.add(networkMetric)

            val locationMetric: AbstractLocationMetric by inject()
            metrics.add(locationMetric)
        }
    }

    override fun onStopJob(params: JobParameters?): Boolean {
        jobFinished(params, true)

        return true
    }

    override fun onStartJob(params: JobParameters?): Boolean {
        Log.d(TAG, "SyncStateTask job has started, gathering metrics...")

        val allMetrics = hashMapOf<String, String>()

        metrics.forEach { uniqueMetric ->
            allMetrics.putAll(uniqueMetric.getMetric())
        }

        Log.d(TAG, "Metrics: $allMetrics")
        Log.d(TAG, "Gathered all metrics, publishing to broker...")

        ListenerService.mqttClient.publishMessage(TOPIC_NAME, allMetrics.toString())

        scheduler.scheduleJob(10000, SyncStateTask::class.java, 102)

        jobFinished(params, false)

        return true
    }

    companion object {
        private const val TAG = "SyncStateTask"
        private const val TOPIC_NAME = "test-topic1"
    }
}