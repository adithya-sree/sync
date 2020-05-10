package com.iota.sync.service.metric.location

import android.location.LocationManager
import com.iota.sync.common.SyncConstants
import com.iota.sync.service.metric.IMetric

/**
 * Created by Adithya S. on 05/08/2020
 */

abstract class AbstractLocationMetric : IMetric {

    abstract val locationManager: LocationManager

    abstract fun getCurrentCoords(): String

    abstract fun isLocationOn(): Boolean

    override fun getMetric(): HashMap<String, String> {
        return HashMap<String, String>().apply {
            put(SyncConstants.CURRENT_COORDINATES, getCurrentCoords())
            put(SyncConstants.LOCATION_ON, isLocationOn().toString())
        }
    }
}