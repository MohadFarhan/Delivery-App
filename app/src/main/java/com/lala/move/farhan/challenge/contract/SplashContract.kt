package com.lala.move.farhan.challenge.contract

import com.lala.move.farhan.challenge.domain.Delivery
import com.lala.move.farhan.challenge.interfaces.onSyncCompleteListener
import java.util.ArrayList

/**
 * Created by Mohammed Farhan on 15-09-2018.
 */
interface SplashContract {
    interface SplashView {
        fun initViews(): Unit
        fun onSuccessOfFetchingDeliveryList(): Unit
    }

    interface SplashPresenter {
    }

    interface SplashModel {
        fun fetchDeliveryList(syncCompleteListener: onSyncCompleteListener): Unit
    }
}