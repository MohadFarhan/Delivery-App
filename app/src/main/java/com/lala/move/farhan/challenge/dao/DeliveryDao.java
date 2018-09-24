/*
 * *
 *  * Created by Mohammed Farhan on 22/9/18 5:20 PM
 *  * Copyright (c) 2018 . All rights reserved.
 *  * Last modified 16/9/18 1:57 PM
 *
 */

package com.lala.move.farhan.challenge.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;


import com.lala.move.farhan.challenge.domain.Delivery;

import java.util.ArrayList;
import java.util.List;


@Dao
public interface DeliveryDao
{


	@Query("SELECT * FROM delivery")
	List<Delivery> getAllDeliveryList();


	@Query("SELECT * FROM delivery WHERE id=:deliveryId")
	Delivery getDeliveryItemById(int deliveryId);

	@Query("SELECT * FROM delivery")
	LiveData<List<Delivery>> getLiveDataAll();

	@Query("DELETE FROM delivery")
	void deleteAll();


	@Insert(onConflict = OnConflictStrategy.REPLACE)
	void insert(Delivery... deliveryHelper);

	@Insert(onConflict = OnConflictStrategy.REPLACE)
	void insertAll(ArrayList<Delivery> deliveryHelperArrayList);


}
