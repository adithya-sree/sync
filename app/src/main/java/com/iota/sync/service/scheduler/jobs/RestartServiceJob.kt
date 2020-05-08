package com.iota.sync.service.scheduler.jobs

import android.app.job.JobParameters
import android.app.job.JobService
import android.util.Log
import com.iota.sync.service.binder.ListenerService

/**
 * Created by Adithya S. on 05/07/2020
 */

class RestartServiceJob : JobService() {

    override fun onStopJob(params: JobParameters?): Boolean {
        jobFinished(params, false)

        return true
    }

    override fun onStartJob(params: JobParameters?): Boolean {
        jobFinished(params, false)

        Log.d(TAG, "Restarting Service Job Started..")
        ListenerService.start(this)

        return true
    }

    companion object {
        private const val TAG = "RestartServiceJob"
    }
}