package com.iota.sync.common.modules

import com.iota.sync.service.metric.battery.AbstractBatteryMetric
import com.iota.sync.service.metric.battery.impl.BatteryMetric
import com.iota.sync.service.metric.location.AbstractLocationMetric
import com.iota.sync.service.metric.location.impl.LocationMetric
import com.iota.sync.service.metric.network.AbstractNetworkMetric
import com.iota.sync.service.metric.network.impl.NetworkMetric
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

            // Network Module
            factory<AbstractNetworkMetric> { NetworkMetric(androidContext()) }

            // Location Module
            factory<AbstractLocationMetric> { LocationMetric(androidContext()) }
        }
    }
}