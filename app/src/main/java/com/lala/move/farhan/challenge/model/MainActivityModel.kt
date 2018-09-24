/*
 * *
 *  * Created by Mohammed Farhan on 22/9/18 5:21 PM
 *  * Copyright (c) 2018 . All rights reserved.
 *  * Last modified 22/9/18 4:35 PM
 *
 */

package com.lala.move.farhan.challenge.model

import android.os.AsyncTask
import com.androidnetworking.AndroidNetworking
import com.androidnetworking.common.Priority
import com.androidnetworking.error.ANError
import com.androidnetworking.interfaces.JSONArrayRequestListener
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.lala.move.farhan.challenge.App
import com.lala.move.farhan.challenge.contract.MainContract
import com.lala.move.farhan.challenge.dao.AppDatabase
import com.lala.move.farhan.challenge.domain.Delivery
import com.lala.move.farhan.challenge.interfaces.onSyncCompleteListener
import org.json.JSONArray
import java.util.ArrayList

/**
 * Created by Mohammed Farhan on 15-09-2018.
 */

class MainActivityModel : MainContract.MainModel {
    override fun refreshDeliveryList(syncCompleteListener: onSyncCompleteListener) {
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
                                /*Delete and add new rows*/
                                database.deleteAll()
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

    override fun getDeliveryList(): ArrayList<Delivery> {
        val deliveryList = AppDatabase.getAppDatabase(App.instance.applicationContext).deliveryDao().allDeliveryList
        return ArrayList(deliveryList)
    }
}