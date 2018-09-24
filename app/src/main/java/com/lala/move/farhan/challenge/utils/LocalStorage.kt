/*
 * *
 *  * Created by Mohammed Farhan on 22/9/18 5:43 PM
 *  * Copyright (c) 2018 . All rights reserved.
 *  * Last modified 29/7/18 9:19 PM
 *
 */

package com.lala.move.farhan.challenge.utils

import android.content.Context
import android.content.SharedPreferences

class LocalStorage
/**
 * @param context
 */
(internal var mContext: Context) {
    internal var sharedPreferences: SharedPreferences
    private val MyPREFERENCES = "AppPreferences"

    init {
        sharedPreferences = mContext.getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE)
        initSettings()
    }

    fun clear() {
        sharedPreferences.edit().clear().apply()
    }


    /**
     * @param param
     * *
     * @param value
     */
    fun put(param: String, value: String) {
        val editor = sharedPreferences.edit()
        editor.putString(param, value)
        editor.commit()
    }

    /**
     * @param param
     * *
     * @return
     */
    operator fun get(param: String): String {
        var value: String? = null
        if (sharedPreferences.contains(param)) {
            value = sharedPreferences.getString(param, "")
        }
        return value as String
    }

    /**

     */
    fun printAllValuesToLog() {
        val keys = sharedPreferences.all

        for (entry in keys.entries) {
        }

    }


    private fun initSettings() {
        val editor = sharedPreferences.edit()
        if (!sharedPreferences.contains(firstUsage)) {
            editor.putString(firstUsage, "true")
            editor.commit()
        }

    }

    companion object {
        /**
         * Parameter Names for Local Storage When you add new parameter, Don't
         * forget to initialize in initSettings() method
         */

        val firstUsage = "firstuse"
    }

}
