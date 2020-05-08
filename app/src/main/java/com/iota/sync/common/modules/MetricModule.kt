package com.iota.sync.common.modules

import com.iota.sync.service.metric.battery.AbstractBatteryMetric
import com.iota.sync.service.metric.battery.impl.BatteryMetric
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.Module
import org.koin.dsl.module

/**
 * Created by Adithya S. on 05/08/2020
 */

object MetricModule : AbstractBaseModule() {

    override fun get(): Module {
        return module {
            // Battery Module
            factory<AbstractBatteryMetric> { BatteryMetric(androidContext()) }
        }
    }
}