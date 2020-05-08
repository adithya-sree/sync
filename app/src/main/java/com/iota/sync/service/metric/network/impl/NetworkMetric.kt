package com.iota.sync.service.metric.network.impl

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.telephony.TelephonyManager
import com.iota.sync.service.metric.network.AbstractNetworkMetric


/**
 * Created by Adithya S. on 05/08/2020
 */

@Suppress("RECEIVER_NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS", "DEPRECATION")
class NetworkMetric (private val context: Context) : AbstractNetworkMetric() {

    override val connectionManager: ConnectivityManager
        get() = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

    override val telephonyManager: TelephonyManager
        get() = context.getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager

    override fun getNetworkType(): String {
        var networkType: String? = "UNKNOWN"

        val activeNetwork =
            connectionManager.activeNetworkInfo
        if (activeNetwork != null && activeNetwork.isConnectedOrConnecting) {
            if (activeNetwork.type == ConnectivityManager.TYPE_WIFI) {
                networkType = "WIFI"
            } else if (activeNetwork.type == ConnectivityManager.TYPE_MOBILE) {
                networkType = activeNetwork.subtypeName
            }
        }
        return networkType!!
    }

    override fun isNetworkConnected(): Boolean {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val nw = connectionManager.activeNetwork ?: return false
            val actNw = connectionManager.getNetworkCapabilities(nw) ?: return false
            return when {
                actNw.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
                actNw.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
                else -> false
            }
        } else {
            val nwInfo = connectionManager.activeNetworkInfo ?: return false
            return nwInfo.isConnected
        }
    }

    override fun getNetworkStatus(): String {
        return when (getNetworkType()) {
            "WIFI" -> {
                connectionManager.getNetworkInfo(1).state.toString()
            }

            else -> {
                connectionManager.getNetworkInfo(0).state.toString()
            }
        }
    }

    override fun getNetworkCarrier(): String {
        return telephonyManager.networkOperatorName
    }
}