package com.iota.sync.common.modules

import com.iota.sync.service.scheduler.IScheduler
import com.iota.sync.service.scheduler.impl.Scheduler
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.Module
import org.koin.dsl.module

/**
 * Created by Adithya S. on 05/07/2020
 */

object SchedulerModule : AbstractBaseModule() {

    override fun get(): Module {
        return module {
            single<IScheduler> { Scheduler(androidContext()) }
        }
    }
}