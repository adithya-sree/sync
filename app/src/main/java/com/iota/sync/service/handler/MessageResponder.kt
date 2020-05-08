package com.iota.sync.service.handler

import android.os.Bundle
import android.os.Message
import android.os.Messenger
import android.os.RemoteException
import android.util.Log

/**
 * Created by Adithya S. on 05/07/2020
 */

object MessageResponder {

    private const val TAG = "MessageResponder"

    fun respond(replyTo: Messenger, bundle: Bundle) {
        val msg = Message.obtain().apply {
            data = bundle
        }

        try {
            replyTo.send(msg)
        } catch (e: RemoteException) {
            Log.e(TAG, "Error while sending response", e)
        }
    }
}