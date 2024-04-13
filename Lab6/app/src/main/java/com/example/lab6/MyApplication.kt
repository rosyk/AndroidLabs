package com.example.lab6

import android.app.Application
import android.util.Log

class MyApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        Log.d("MyApplication", "App is running")
    }
}