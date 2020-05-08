package com.iota.sync.service.handler

import android.os.IBinder
import android.os.Message

/**
 * Created by Adithya S. on 05/07/2020
 */

interface IRequestHandler {

    enum class RequestTypes(val type: Int) {
        PublishRequest(1)
    }

    fun handleUnknownRequest(msg: Message)

    fun handlePublishMessage(msg: Message)

    fun routeRequest(msg: Message)

    fun getBinder(): IBinder
}