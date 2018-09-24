/*
 * *
 *  * Created by Mohammed Farhan on 22/9/18 5:19 PM
 *  * Copyright (c) 2018 . All rights reserved.
 *  * Last modified 22/9/18 5:15 PM
 *
 */

package com.lala.move.farhan.challenge.activities

import android.opengl.Visibility
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.support.v7.widget.LinearLayoutManager
import android.view.Menu
import android.view.MenuItem
import android.view.View.*
import android.widget.Toast
import com.lala.move.farhan.challenge.R
import com.lala.move.farhan.challenge.adapters.DeliveryItemAdapter
import com.lala.move.farhan.challenge.contract.MainContract
import com.lala.move.farhan.challenge.domain.Delivery
import com.lala.move.farhan.challenge.presenter.MainActivityPresenter
import com.lala.move.farhan.challenge.view.MainActivityView
import kotlinx.android.synthetic.main.activity_main.*
import java.util.ArrayList


class MainActivity : MainContract.MainView, AppCompatActivity() {
    var mainActivityPresenter: MainActivityPresenter? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainActivityPresenter = MainActivityPresenter(this)

    }


    override fun onResume() {
        super.onResume()
    }

    /**
     * Method to show delivery items in recyclerview
     */
    override fun showDeliveryList(deliveryArrayList: ArrayList<Delivery>) {
        runOnUiThread(Runnable {
            if (deliveryArrayList != null && deliveryArrayList.size > 0) {
                apptoolBarSubtitleTV.text = "Pending Deliveries " + deliveryArrayList.size
                Handler().postDelayed(Runnable {
                    val deliveryAdatpter = DeliveryItemAdapter(deliveryArrayList, this)
                    deliveryListRV.adapter = deliveryAdatpter

                    shimmer_view_container.stopShimmerAnimation()
                    shimmer_view_container.visibility = GONE
                }, 500)
            }
        })

    }


    /**
     * Method to Init Views
     */
    override fun initViews() {
        setContentView(R.layout.activity_main)
//        setSupportActionBar(toolbar)
//        toolbar.setSubtitleTextAppearance(this@MainActivity, R.style.toolbarSubTitle)
        shimmer_view_container.startShimmerAnimation()
        deliveryListRV.setHasFixedSize(true)
        deliveryListRV.layoutManager = LinearLayoutManager(this)

        refreshIV.setOnClickListener {
            mainActivityPresenter!!.onRefreshClick()
            Toast.makeText(this, "Refreshing Delivery Items Please Wait", Toast.LENGTH_LONG).show()
        }
    }


    /**
     * Method to show shimmer effect
     */
    override fun showProgress() {
        runOnUiThread(Runnable {
            shimmer_view_container.visibility = VISIBLE
            deliveryListRV.visibility = GONE
            shimmer_view_container.startShimmerAnimation()
        })
    }

    /**
     * Method to hide shimmer effect after refreshing delivery items
     */
    override fun hideProgress() {
        runOnUiThread(Runnable {
            shimmer_view_container.visibility = GONE
            deliveryListRV.visibility = VISIBLE
            shimmer_view_container.stopShimmerAnimation()
        })
    }

}
