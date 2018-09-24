/*
 * *
 *  * Created by Mohammed Farhan on 22/9/18 5:22 PM
 *  * Copyright (c) 2018 . All rights reserved.
 *  * Last modified 22/9/18 4:35 PM
 *
 */

package com.lala.move.farhan.challenge.presenter

import com.lala.move.farhan.challenge.contract.MainContract
import com.lala.move.farhan.challenge.interfaces.onSyncCompleteListener
import com.lala.move.farhan.challenge.model.MainActivityModel


class MainActivityPresenter(val mainView: MainContract.MainView) : MainContract.MainPresenter {
    override fun onRefreshClick() {


        val mainModel = MainActivityModel()
        if (mainView != null) {
            mainView.showProgress()
            mainModel.refreshDeliveryList(object : onSyncCompleteListener {
                override fun syncSuccess() {
                    mainView.showDeliveryList(mainModel.getDeliveryList())
                    mainView.hideProgress()
                }
            })
        }

    }

    init {
        initMainActivityPresenter(mainView)
    }

    override fun onCardClick(cardId: Int) {
    }
}

private fun initMainActivityPresenter(mainView: MainContract.MainView) {
    val mainModel = MainActivityModel()
    mainView.initViews()
    mainView.showDeliveryList(mainModel.getDeliveryList())
}
