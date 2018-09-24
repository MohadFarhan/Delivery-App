/*
 * *
 *  * Created by Mohammed Farhan on 22/9/18 5:19 PM
 *  * Copyright (c) 2018 . All rights reserved.
 *  * Last modified 22/9/18 4:27 PM
 *
 */

package com.lala.move.farhan.challenge.activities

import android.Manifest
import android.app.PendingIntent.getActivity
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.lala.move.farhan.challenge.R
import com.lala.move.farhan.challenge.domain.Delivery
import android.content.pm.PackageManager
import android.graphics.Paint
import android.support.v4.content.ContextCompat
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.model.CameraPosition
import android.widget.Toast
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import jp.wasabeef.glide.transformations.CropCircleTransformation
import kotlinx.android.synthetic.main.activity_delivery_details.*


class DeliveryDetailsActivity : AppCompatActivity() {

    private var googleMap: GoogleMap? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_delivery_details)

        val delivery: Delivery = intent.getSerializableExtra("delivery") as Delivery;
        if (delivery != null) {
            val latitude: Double = delivery.location!!.lat as Double
            val longitude: Double = delivery.location!!.lng as Double
            initializeMapAndData(delivery, LatLng(latitude, longitude))
        }


    }

    /**
     * function to load map and delivery details.
     *
     */
    private fun initializeMapAndData(delivery: Delivery, latLng: LatLng) {

        deliveryDescriptionTV.text = delivery.description
        addressTV.text = "Address " + delivery.location!!.address

        Glide.with(this@DeliveryDetailsActivity).load(delivery.imageUrl).apply(RequestOptions.bitmapTransform(CropCircleTransformation())).into(deliveryImageIV)


        if (googleMap == null) {

            val mapFragment = supportFragmentManager
                    .findFragmentById(R.id.map) as SupportMapFragment
            mapFragment.getMapAsync(OnMapReadyCallback { googleMap ->
                // check if map is created successfully or not
                if (googleMap == null) {
                    Toast.makeText(applicationContext,
                            "Unable to Create Map",
                            Toast.LENGTH_SHORT).show()
                    return@OnMapReadyCallback
                }
                this@DeliveryDetailsActivity.googleMap = googleMap

                if (latLng != null && (latLng.latitude != 0.0 || latLng.longitude != 0.0)) {
                    //Animate Camera to Current Location
                    val cameraPosition = CameraPosition.Builder()
                            .target(latLng)
                            .zoom(15f)
                            .build()
                    googleMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition),
                            1000, null)

                    //Add Marker on Current Location
                    val marker = MarkerOptions()
                            .position(latLng)
                            .title(delivery.description + " at " + delivery.location!!.address)
                            .snippet("lattitude " + latLng.latitude + " longitutde " + latLng.longitude)
                    googleMap.addMarker(marker) // adding marker
                }

                if (ContextCompat.checkSelfPermission(this@DeliveryDetailsActivity,
                        Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                    googleMap.isMyLocationEnabled = false
                }

                googleMap.uiSettings.isCompassEnabled = true
                googleMap.uiSettings.isZoomControlsEnabled = true
                googleMap.uiSettings.isMyLocationButtonEnabled = true
                googleMap.uiSettings.isRotateGesturesEnabled = false
            })
        }
    }
}
