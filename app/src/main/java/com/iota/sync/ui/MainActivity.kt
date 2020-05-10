package com.iota.sync.ui

import android.app.Activity
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.iota.sync.R
import com.iota.sync.service.binder.ListenerService

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        ListenerService.start(this)

        activity = this

    }

    companion object {
        lateinit var activity: Activity
    }
}
