/*
 * *
 *  * Created by Mohammed Farhan on 22/9/18 5:21 PM
 *  * Copyright (c) 2018 . All rights reserved.
 *  * Last modified 22/9/18 4:35 PM
 *
 */

package com.lala.move.farhan.challenge.interfaces;

import com.google.android.gms.maps.model.LatLng;


/**
 * Interface to listen when location of user is obtained via GPS
 */
public interface LocListener
{
	void onLocationReceived(LatLng latLng);
}
