package com.iota.sync.common.modules

import com.iota.sync.service.mqtt.IMQTTClient
import com.iota.sync.service.mqtt.impl.MQTTClient
import org.koin.core.module.Module
import org.koin.dsl.module
import java.util.*

/**
 * Created by Adithya S. on 05/07/2020
 */

object MQTTModule : AbstractBaseModule() {

    override fun get(): Module {
        return module {
            factory<IMQTTClient> {
                provideMQTTClient()
            }
        }
    }

    private const val SERVER_URL = "tcp://35.232.128.21:1883"

    private fun provideMQTTClient(): MQTTClient {
        return MQTTClient(SERVER_URL, UUID.randomUUID().toString())
    }
}