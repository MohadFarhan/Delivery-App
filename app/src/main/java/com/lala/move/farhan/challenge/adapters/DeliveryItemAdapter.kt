/*
 * *
 *  * Created by Mohammed Farhan on 22/9/18 5:20 PM
 *  * Copyright (c) 2018 . All rights reserved.
 *  * Last modified 22/9/18 4:35 PM
 *
 */

package com.lala.move.farhan.challenge.adapters

import android.app.Activity
import android.app.ActivityOptions
import android.content.Context
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.google.gson.Gson
import com.lala.move.farhan.challenge.R
import com.lala.move.farhan.challenge.domain.Delivery
import java.util.ArrayList
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.lala.move.farhan.challenge.App
import com.lala.move.farhan.challenge.activities.DeliveryDetailsActivity
import com.lala.move.farhan.challenge.dao.AppDatabase
import jp.wasabeef.glide.transformations.CropCircleTransformation
import kotlinx.android.synthetic.main.delivery_items.view.*


/**
 * Created by Mohammed Farhan on 15-09-2018.
 */

class DeliveryItemAdapter(val deliveryItemList: ArrayList<Delivery>, val context: Context) : RecyclerView.Adapter<DeliveryItemAdapter.ViewHolder>() {

    override fun getItemCount(): Int {
        return deliveryItemList.size
    }

    override fun onBindViewHolder(holder: DeliveryItemAdapter.ViewHolder?, position: Int) {

        val delivery = deliveryItemList.get(position)
        holder?.deliveryIdTV?.text = delivery.id.toString()
        holder?.deliveryDescriptionTV?.text = delivery.description

        val gson = Gson()
        holder?.deliveryaddressTV?.text = "Address: " + delivery.location?.address

        var requestOptions = RequestOptions()
        requestOptions = requestOptions.transforms(RoundedCorners(16))
        Glide.with(context).load(delivery.imageUrl).apply(RequestOptions.bitmapTransform(CropCircleTransformation())).into(holder!!.deliveryImageIV)
        holder.itemView.setOnClickListener {

            val id: String = holder.deliveryIdTV.text.toString()
            val delivery: Delivery = AppDatabase.getAppDatabase(App.instance.applicationContext).deliveryDao().getDeliveryItemById(Integer.parseInt(id))
            if (delivery != null) {
                val iamgeViewPair = android.util.Pair.create(holder.itemView.deliveryImageIV as View, context.getString(R.string.personImageViewtransition))
                val cardViewPair = android.util.Pair.create(holder.itemView.cardLayout as View, context.getString(R.string.cardViewtransition))
                val deliveryDescriptiontextPair = android.util.Pair.create(holder.itemView.deliveryDescriptionTV as View, context.getString(R.string.textViewTransition))
                val addressPair = android.util.Pair.create(holder.itemView.addressTV as View, context.getString(R.string.addressTextViewTransition))

                val intent = Intent(context, DeliveryDetailsActivity::class.java)
                intent.putExtra("delivery", delivery)
                val options: ActivityOptions = ActivityOptions.makeSceneTransitionAnimation(context as Activity,
                        iamgeViewPair, deliveryDescriptiontextPair, addressPair)
                context.startActivity(intent, options.toBundle());
            }
        }


    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): DeliveryItemAdapter.ViewHolder {

        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.delivery_items, parent, false))
    }

    class ViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView) {
        val deliveryIdTV = itemView!!.findViewById<TextView>(R.id.deliveryIdTV)
        val deliveryDescriptionTV = itemView!!.findViewById<TextView>(R.id.deliveryDescriptionTV)
        val deliveryaddressTV = itemView!!.findViewById<TextView>(R.id.addressTV)
        val deliveryImageIV = itemView!!.findViewById<ImageView>(R.id.deliveryImageIV)
    }

}