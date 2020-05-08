package com.iota.sync.service.metric.network

import android.net.ConnectivityManager
import android.telephony.TelephonyManager
import com.iota.sync.service.metric.IMetric

/**
 * Created by Adithya S. on 05/08/2020
 */

abstract class AbstractNetworkMetric : IMetric {

    abstract val connectionManager: ConnectivityManager

    abstract val telephonyManager: TelephonyManager

    abstract fun getNetworkType(): String

    abstract fun isNetworkConnected(): Boolean

    abstract fun getNetworkStatus(): String

    abstract fun getNetworkCarrier(): String

    override fun getMetric(): HashMap<String, String> {
        return HashMap<String, String>().apply {
            put(NETWORK_CONNECTED, isNetworkConnected().toString())
            put(NETWORK_TYPE, getNetworkType())
            put(NETWORK_STATUS, getNetworkStatus())
            put(NETWORK_CARRIER, getNetworkCarrier())
        }
    }

    companion object {
        private const val NETWORK_TYPE = "network-type"
        private const val NETWORK_CONNECTED = "network-connected"
        private const val NETWORK_STATUS = "network-status"
        private const val NETWORK_CARRIER = "network-carrier"
    }
}