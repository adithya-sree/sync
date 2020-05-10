package com.iota.sync.service.metric.battery

import android.os.BatteryManager
import com.iota.sync.common.SyncConstants
import com.iota.sync.service.metric.IMetric

/**
 * Created by Adithya S. on 05/08/2020
 */

abstract class AbstractBatteryMetric : IMetric {

    abstract val batteryManager: BatteryManager

    abstract fun getBatteryPercent(): String

    abstract fun isCharging(): Boolean

    override fun getMetric(): HashMap<String, String> {
        return HashMap<String, String>().apply {
            put(SyncConstants.BATTERY_PERCENT, getBatteryPercent())
            put(SyncConstants.CHARGING, isCharging().toString())
        }
    }
}