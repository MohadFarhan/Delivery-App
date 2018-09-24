/*
 * *
 *  * Created by Mohammed Farhan on 22/9/18 5:22 PM
 *  * Copyright (c) 2018 . All rights reserved.
 *  * Last modified 16/9/18 11:48 AM
 *
 */

package com.lala.move.farhan.challenge.presenter

import com.lala.move.farhan.challenge.App
import com.lala.move.farhan.challenge.contract.SplashContract
import com.lala.move.farhan.challenge.interfaces.onSyncCompleteListener
import com.lala.move.farhan.challenge.model.SplashActivityModel
import com.lala.move.farhan.challenge.utils.LocalStorage
import java.util.*


public class SplashActivityPresenter(splashView: SplashContract.SplashView) : SplashContract.SplashPresenter {

    init {
        initializePresenter(splashView)
    }
}

public fun initializePresenter(splashView: SplashContract.SplashView): Unit {

    splashView.initViews()
    val splashModel = SplashActivityModel()
    splashModel.fetchDeliveryList(object : onSyncCompleteListener {
        override fun syncSuccess() {
            splashView.onSuccessOfFetchingDeliveryList()
        }
    })
}