package com.iota.sync.service.mqtt

import org.eclipse.paho.client.mqttv3.MqttCallback
import org.eclipse.paho.client.mqttv3.MqttClient

/**
 * Created by Adithya S. on 03/28/2020
 */
interface IMQTTClient {

    val client: MqttClient

    /**
     * Establishes a connection to the messaging MQTT broker.
     * Uses the Paho libraries defaults when connecting
     *
     * @return Success - If the connection to the MQTT Broker was successful
     */
    fun connectToBroker(): Boolean

    /**
     * Disconnects from the current connected MQTT Broker
     * Will return true if no active MQTT connections exists
     *
     * @return Success - If the disconnect from the MQTT Broker was successful
     */
    fun disconnectFromBroker(): Boolean

    /**
     * Subscribes to a topic on the MQTT broker
     * Will connect to broker if connection does not exists
     *
     * @param topicName - The name of the topic you want to subscribe to
     * @return Success - If the subscription was successful
     */
    fun subscribeToTopic(topicName: String): Boolean

    /**
     * Unsubscribe to a topic on the MQTT broker
     * Will return false if no active MQTT connection exists
     *
     * @param topicName - The name of the topic you want to unsubscribe from
     * @return Success - If the unsubscribe was successful
     */
    fun unsubscribeFromTopic(topicName: String): Boolean

    /**
     * Publishes the given message to the topic on the MQTT Broker
     *
     * @param topicName - The name of the topic you want to publish to
     * @param message - The message you want to publish to
     */
    fun publishMessage(topicName: String, message: String): Boolean


    /**
     * Sets the message callback
     *
     * @param mqttCallback - the callback
     */
    fun setCallback(mqttCallback: MqttCallback)
}