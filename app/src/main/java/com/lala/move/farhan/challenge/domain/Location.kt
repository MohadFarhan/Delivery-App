/*
 * *
 *  * Created by Mohammed Farhan on 22/9/18 5:20 PM
 *  * Copyright (c) 2018 . All rights reserved.
 *  * Last modified 22/9/18 4:35 PM
 *
 */

package com.lala.move.farhan.challenge.domain

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import android.support.annotation.NonNull
import java.io.Serializable


class Location : Serializable {


    var lat: Double? = null

    var lng: Double? = null

    var address: String? = null


}