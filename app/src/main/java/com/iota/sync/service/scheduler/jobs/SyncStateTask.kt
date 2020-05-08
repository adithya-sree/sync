package com.iota.sync.service.scheduler.jobs

import android.app.job.JobParameters
import android.app.job.JobService
import com.iota.sync.service.binder.ListenerService
import com.iota.sync.service.metric.IMetric
import com.iota.sync.service.metric.battery.AbstractBatteryMetric
import com.iota.sync.service.metric.network.AbstractNetworkMetric
import org.koin.android.ext.android.inject

/**
 * Created by Adithya S. on 05/08/2020
 */

class SyncStateTask : JobService() {

    private val metrics: ArrayList<IMetric> = arrayListOf()

    init {
        metrics.apply {
            val batteryMetric: AbstractBatteryMetric by inject()
            metrics.add(batteryMetric)

            val networkMetric: AbstractNetworkMetric by inject()
            metrics.add(networkMetric)
        }
    }

    override fun onStopJob(params: JobParameters?): Boolean {
        jobFinished(params, true)

        return true
    }

    override fun onStartJob(params: JobParameters?): Boolean {
        ListenerService.mqttClient.publishMessage("topic1", metrics.toString())

        jobFinished(params, true)

        return true
    }
}