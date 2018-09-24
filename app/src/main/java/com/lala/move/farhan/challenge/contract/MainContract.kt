/*
 * *
 *  * Created by Mohammed Farhan on 22/9/18 5:20 PM
 *  * Copyright (c) 2018 . All rights reserved.
 *  * Last modified 22/9/18 4:35 PM
 *
 */

package com.lala.move.farhan.challenge.contract

import com.lala.move.farhan.challenge.domain.Delivery
import com.lala.move.farhan.challenge.interfaces.onSyncCompleteListener
import java.util.ArrayList

interface MainContract {
    interface MainView {
        fun initViews(): Unit
        fun showProgress(): Unit
        fun hideProgress(): Unit
        fun showDeliveryList(deliveryArrayList: ArrayList<Delivery>): Unit
    }

    interface MainPresenter {

        fun onCardClick(cardId: Int): Unit
        fun onRefreshClick(): Unit
    }

    interface MainModel {
        fun getDeliveryList(): ArrayList<Delivery>
        fun refreshDeliveryList(syncCompleteListener: onSyncCompleteListener): Unit
    }
}