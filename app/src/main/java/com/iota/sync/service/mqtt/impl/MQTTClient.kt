package com.iota.sync.service.mqtt.impl

import android.util.Log
import com.iota.sync.service.mqtt.IMQTTClient
import org.eclipse.paho.client.mqttv3.MqttCallback
import org.eclipse.paho.client.mqttv3.MqttClient
import org.eclipse.paho.client.mqttv3.MqttException
import org.eclipse.paho.client.mqttv3.MqttMessage
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence

/**
 * Created by Adithya S. on 03/28/2020
 */
class MQTTClient(serverUrl: String, clientId: String) : IMQTTClient {

    override val client: MqttClient = MqttClient(
        serverUrl,
        clientId,
        MemoryPersistence()
    )

    override fun connectToBroker(): Boolean {
        if (!isConnected) {
            try {
                client.connect()
                isConnected = true
                Log.d(TAG, "Successfully connected to broker")
            } catch (e: MqttException) {
                isConnected = false
                Log.e(TAG, "Error while connecting to broker $e")
            }
        }
        return isConnected
    }

    override fun disconnectFromBroker(): Boolean {
        if (isConnected) {
            try {
                client.disconnect()
                isConnected = false
                Log.d(TAG, "Successfully disconnected from broker")
            } catch (e: MqttException) {
                Log.e(TAG, "Error while disconnecting from broker $e")
                return false
            }
        }
        return true
    }

    override fun subscribeToTopic(topicName: String): Boolean {
        try {
            client.subscribe(topicName)
            Log.d(TAG, "Successfully subscribed to topic $topicName")
        } catch (e: MqttException) {
            Log.e(TAG, "Error while subscribing to topic $e")
            return false
        }

        return true
    }

    override fun unsubscribeFromTopic(topicName: String): Boolean {
        try {
            client.unsubscribe(topicName)
            Log.d(TAG, "Successfully unsubscribed to topic $topicName")
        } catch (e: MqttException) {
            Log.e(TAG, "Error while unsubscribe to topic $e")
            return false
        }
        return true
    }

    override fun publishMessage(topicName: String, message: String): Boolean {
        val mqMessage = MqttMessage().apply {
            payload = message.toByteArray()
        }

        try {
            client.publish(topicName, mqMessage)
            Log.d(TAG, "Successfully published message to topic")
        } catch (e: MqttException) {
            Log.e(TAG, "Error while publishing message to topic $e")
            return false
        }

        return true
    }

    override fun setCallback(mqttCallback: MqttCallback) {
        this.client.setCallback(mqttCallback)
    }

    companion object {
        private const val TAG = "MQTTClient"

        var isConnected = false
    }
}