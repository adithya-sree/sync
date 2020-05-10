package com.iota.sync.service.metric.location.impl

import android.annotation.SuppressLint
import android.content.Context
import android.location.Location
import android.location.LocationManager
import android.provider.Settings
import android.text.TextUtils
import com.iota.sync.service.metric.location.AbstractLocationMetric

/**
 * Created by Adithya S. on 05/08/2020
 */

class LocationMetric (private val context: Context) : AbstractLocationMetric() {

    override val locationManager: LocationManager
        get() = context.getSystemService(Context.LOCATION_SERVICE) as LocationManager

    @SuppressLint("MissingPermission")
    override fun getCurrentCoords(): String {
        val location: Location? = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER)

        location?.let {
            val longitude: Double = location.longitude
            val latitude: Double = location.latitude

            return "$latitude, $longitude"
        }

        return "N/A"
    }

    override fun isLocationOn(): Boolean {
        val locationProviders: String =
            Settings.Secure.getString(context.contentResolver, Settings.Secure.LOCATION_PROVIDERS_ALLOWED);

        return !TextUtils.isEmpty(locationProviders)
    }
}