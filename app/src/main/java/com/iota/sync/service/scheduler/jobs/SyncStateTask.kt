package com.iota.sync.service.scheduler.jobs

import android.app.job.JobParameters
import android.app.job.JobService
import com.iota.sync.service.metric.IMetric
import com.iota.sync.service.metric.battery.AbstractBatteryMetric
import org.koin.android.ext.android.inject

/**
 * Created by Adithya S. on 05/08/2020
 */

class SyncStateTask : JobService() {

    private val metrics: ArrayList<IMetric> = arrayListOf()

    init {
        metrics.apply {
            val batteryMetric: AbstractBatteryMetric by inject()
            add(batteryMetric)
        }
    }

    override fun onStopJob(params: JobParameters?): Boolean {
        TODO("Not yet implemented")
    }

    override fun onStartJob(params: JobParameters?): Boolean {
        TODO("Not yet implemented")
    }
}