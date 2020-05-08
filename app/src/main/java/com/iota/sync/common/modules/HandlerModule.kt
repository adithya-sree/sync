package com.iota.sync.common.modules

import com.iota.sync.service.handler.IRequestHandler
import com.iota.sync.service.handler.impl.RequestHandler
import org.koin.core.module.Module
import org.koin.dsl.module

/**
 * Created by Adithya S. on 05/07/2020
 */

object HandlerModule : AbstractBaseModule() {

    override fun get(): Module {
        return module {
            single<IRequestHandler> { RequestHandler() }
        }
    }
}