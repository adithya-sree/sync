package com.iota.sync.service.scheduler

import android.app.job.JobInfo

/**
 * Created by Adithya S. on 05/07/2020
 */
interface IScheduler {
    fun scheduleJob(scheduleIn: Long, clazz: Class<*>, jobId: Int)

    fun getScheduledJobs(): List<JobInfo>

    fun cancelJob(jobId: Int)

    fun cancelAllJobs()
}