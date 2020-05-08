package com.iota.sync.service.metric

/**
 * Created by Adithya S. on 05/08/2020
 */

interface IMetric {

    fun getMetric(): HashMap<String, String>
}