package com.iota.sync

import android.app.Application
import com.iota.sync.common.modules.HandlerModule
import com.iota.sync.common.modules.MQTTModule
import com.iota.sync.common.modules.MetricModule
import com.iota.sync.common.modules.SchedulerModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.core.module.Module

/**
 * Created by Adithya S. on 05/07/2020
 */

class SyncApplication : Application() {

    private val modules: List<Module> = listOf(
        MQTTModule.get(),
        SchedulerModule.get(),
        HandlerModule.get(),
        MetricModule.get()
    )

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@SyncApplication)
            modules(modules)
        }
    }
}