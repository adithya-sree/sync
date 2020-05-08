package com.iota.sync.service.metric.battery.impl

import android.content.Context
import android.content.Context.BATTERY_SERVICE
import android.os.BatteryManager
import com.iota.sync.service.metric.battery.AbstractBatteryMetric

/**
 * Created by Adithya S. on 05/08/2020
 */

class BatteryMetric(private val context: Context) : AbstractBatteryMetric() {

    override val batteryManager: BatteryManager
        get() = context.getSystemService(BATTERY_SERVICE) as BatteryManager

    override fun getBatteryPercent(): String {
        return batteryManager.getIntProperty(BatteryManager.BATTERY_PROPERTY_CAPACITY).toString()
    }

    override fun isCharging(): Boolean {
        return batteryManager.isCharging
    }
}