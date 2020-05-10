package com.iota.sync.service.handler.impl

import android.os.*
import android.util.Log
import com.iota.sync.service.handler.IRequestHandler
import com.iota.sync.service.handler.MessageResponder

/**
 * Created by Adithya S. on 05/07/2020
 */

class RequestHandler : Handler(), IRequestHandler {

    override fun handleUnknownRequest(msg: Message) {
        Log.d(TAG, "Unknown request type ${msg.what}")
        MessageResponder.respond(msg.replyTo, Bundle())
    }

    override fun handlePublishMessage(msg: Message) {
        TODO()
    }

    override fun routeRequest(msg: Message) {
        when (msg.what) {
            IRequestHandler.RequestTypes.PublishRequest.type -> {
                handlePublishMessage(msg)
            }

            else -> {
                handleUnknownRequest(msg)
            }
        }
    }

    override fun getBinder(): IBinder {
        return Messenger(this).binder
    }

    companion object {
        private const val TAG = "RequestHandler"
    }
}