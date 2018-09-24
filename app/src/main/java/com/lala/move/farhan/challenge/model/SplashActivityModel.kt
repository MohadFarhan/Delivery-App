/*
 * *
 *  * Created by Mohammed Farhan on 22/9/18 5:21 PM
 *  * Copyright (c) 2018 . All rights reserved.
 *  * Last modified 22/9/18 4:35 PM
 *
 */

package com.lala.move.farhan.challenge.model


import android.app.usage.UsageEvents
import android.os.AsyncTask
import com.lala.move.farhan.challenge.contract.MainContract
import com.lala.move.farhan.challenge.contract.SplashContract
import com.lala.move.farhan.challenge.domain.Delivery
import java.util.ArrayList
import com.androidnetworking.error.ANError
import org.json.JSONArray
import com.androidnetworking.interfaces.JSONArrayRequestListener
import com.androidnetworking.AndroidNetworking
import com.androidnetworking.common.Priority
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import com.lala.move.farhan.challenge.App
import com.lala.move.farhan.challenge.dao.AppDatabase
import com.lala.move.farhan.challenge.domain.Location
import com.lala.move.farhan.challenge.interfaces.onSyncCompleteListener
import org.json.JSONObject
import java.lang.reflect.Type


/**
 * Created by Mohammed Farhan on 15-09-2018.
 */


/**
 * Method to fetch arraylist of delivery items from mock server
 */
class SplashActivityModel : SplashContract.SplashModel {
    override fun fetchDeliveryList(syncCompleteListener: onSyncCompleteListener) {

        /*Mock Url to fetch delivery list starting from 0 offset upto 25 limit*/

        val url: String = "https://mock-api-mobile.dev.lalamove.com/deliveries?offset=0&limit=25"
        AndroidNetworking.get(url)
                .setTag("Mock API")
                .setPriority(Priority.HIGH)
                .build()
                .getAsJSONArray(object : JSONArrayRequestListener {
                    override fun onResponse(response: JSONArray) {
                        // do anything with response
                        if (response != null && response.length() != 0) {
                            val deliveryType = object : TypeToken<ArrayList<Delivery>>() {}.type
                            val deliveryGson = Gson().fromJson<ArrayList<Delivery>>(response.toString(), deliveryType)
                            AsyncTask.execute {
                                val database = AppDatabase.getAppDatabase(App.instance.applicationContext).deliveryDao();
                                database.insertAll(deliveryGson)
                                syncCompleteListener.syncSuccess()
                            }

                        }
                    }

                    override fun onError(error: ANError) {
                        // handle error
                    }
                })
    }
}



