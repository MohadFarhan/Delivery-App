/*
 * *
 *  * Created by Mohammed Farhan on 22/9/18 5:22 PM
 *  * Copyright (c) 2018 . All rights reserved.
 *  * Last modified 16/9/18 12:47 PM
 *
 */

package com.lala.move.farhan.challenge


import android.app.Application
import android.support.multidex.MultiDex
import com.androidnetworking.AndroidNetworking

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        instance = this;
        AndroidNetworking.initialize(getApplicationContext());
        MultiDex.install(instance)
    }

    companion object {
        lateinit var instance: App
    }
}