package com.iota.sync.service.scheduler.impl

import android.app.job.JobInfo
import android.app.job.JobScheduler
import android.content.ComponentName
import android.content.Context
import com.iota.sync.service.scheduler.IScheduler

/**
 * Created by Adithya S. on 05/07/2020
 */
class Scheduler (private val context: Context) : IScheduler {

    private val jobScheduler: JobScheduler? = context.getSystemService(JobScheduler::class.java)

    override fun scheduleJob(scheduleIn: Long, clazz: Class<*>, jobId: Int) {
        val builder =
            JobInfo.Builder(jobId, ComponentName(context, clazz)).apply {
                setMinimumLatency(scheduleIn)
            }

        jobScheduler?.schedule(builder.build())
    }

    override fun getScheduledJobs(): List<JobInfo> {
        return jobScheduler?.allPendingJobs!!
    }

    override fun cancelJob(jobId: Int) {
        jobScheduler?.cancel(jobId)
    }

    override fun cancelAllJobs() {
        jobScheduler?.cancelAll()
    }
}