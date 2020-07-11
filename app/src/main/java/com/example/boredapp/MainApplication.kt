package com.example.boredapp

import android.app.Application
import android.content.Context
import com.example.boredapp.data.local.BoredAppDB

class MainApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        instance = this
        db = BoredAppDB.getInstance(this)
    }

    companion object {

        lateinit var instance: MainApplication
            private set

        lateinit var db:BoredAppDB
            private set

        fun applicationContext() : Context {
            return instance.applicationContext
        }

    }

}