package com.iota.sync.common.modules

import org.koin.core.module.Module
import org.koin.dsl.module

/**
 * Created by Adithya S. on 05/07/2020
 */

abstract class AbstractBaseModule {

    open fun get(): Module {
        return module {

        }
    }
}