package com.iota.sync.service.binder

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.Service
import android.content.Context
import android.graphics.Color
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationCompat.PRIORITY_MIN
import com.iota.sync.R

/**
 * Created by Adithya S. on 05/07/2020
 */

object ForegroundServiceUtil {

    fun startForeground(service: Service, serviceChannelId: String, channelName: String, contentTitle: String, contextDesc: String) {
        val channelId =
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                createNotificationChannel(serviceChannelId, channelName, service)
            } else {
                ""
            }

        val notificationBuilder = NotificationCompat.Builder(service, channelId )
        val notification = notificationBuilder.setOngoing(true)
            .setContentTitle(contentTitle)
            .setContentText(contextDesc)
            .setSmallIcon(R.mipmap.ic_launcher)
            .setPriority(PRIORITY_MIN)
            .setCategory(Notification.CATEGORY_SERVICE)
            .build()

        service.startForeground(101, notification)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun createNotificationChannel(channelId: String, channelName: String, context: Context): String{
        val chan = NotificationChannel(channelId,
            channelName, NotificationManager.IMPORTANCE_NONE)
        chan.lightColor = Color.BLUE
        chan.lockscreenVisibility = Notification.VISIBILITY_PRIVATE
        val service = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        service.createNotificationChannel(chan)
        return channelId
    }
}