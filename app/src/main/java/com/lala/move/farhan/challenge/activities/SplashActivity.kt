/*
 * *
 *  * Created by Mohammed Farhan on 22/9/18 5:19 PM
 *  * Copyright (c) 2018 . All rights reserved.
 *  * Last modified 22/9/18 5:17 PM
 *
 */


package com.lala.move.farhan.challenge.activities

import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.lala.move.farhan.challenge.App
import com.lala.move.farhan.challenge.R
import com.lala.move.farhan.challenge.contract.SplashContract
import com.lala.move.farhan.challenge.presenter.SplashActivityPresenter
import com.lala.move.farhan.challenge.utils.LocalStorage

class SplashActivity : SplashContract.SplashView, AppCompatActivity() {
    var splashActivityPresenter: SplashContract.SplashPresenter? = null

    override fun initViews() {
        setContentView(R.layout.activity_splash)
    }

    override fun onSuccessOfFetchingDeliveryList() {
        val localStorage: LocalStorage = LocalStorage(this)
        localStorage.put(LocalStorage.firstUsage, "false")
        val intent: Intent = Intent(this@SplashActivity, MainActivity::class.java)
        startActivity(intent)
        finish()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (!isNetworkAvailable()) {
            Toast.makeText(this, "Please Connnect To Internet", Toast.LENGTH_LONG).show()
        }
    }

    private fun isNetworkAvailable(): Boolean {
        val connectivityManager = getSystemService(Context.CONNECTIVITY_SERVICE)
        return if (connectivityManager is ConnectivityManager) {
            val networkInfo: NetworkInfo? = connectivityManager.activeNetworkInfo
            networkInfo?.isConnected ?: false
        } else false
    }

    override fun onResume() {
        super.onResume()

        val localStorage: LocalStorage = LocalStorage(App.instance.applicationContext)
        val firstTimeUsage: String = localStorage.get(LocalStorage.firstUsage)

        if (firstTimeUsage == "true") {
            splashActivityPresenter = SplashActivityPresenter(this)
        } else {
            val localStorage: LocalStorage = LocalStorage(this)
            localStorage.put(LocalStorage.firstUsage, "false")
            val intent: Intent = Intent(this@SplashActivity, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
    }


}
