/*
 * *
 *  * Created by Mohammed Farhan on 22/9/18 5:20 PM
 *  * Copyright (c) 2018 . All rights reserved.
 *  * Last modified 16/9/18 12:27 PM
 *
 */

package com.lala.move.farhan.challenge.dao;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverters;
import android.content.Context;

import com.lala.move.farhan.challenge.domain.Delivery;
import com.lala.move.farhan.challenge.domain.Location;


@Database(entities = {Delivery.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase
{
	private static AppDatabase INSTANCE;

	public abstract DeliveryDao deliveryDao();

	public static AppDatabase getAppDatabase(Context context)
	{
		if (INSTANCE == null)
		{
			INSTANCE =
					Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class, "exp")
							// allow queries on the main thread.
							// Don't do this on a real app! See PersistenceBasicSample for an example.
							.allowMainThreadQueries()
							.build();
		}
		return INSTANCE;
	}

	public static void destroyInstance()
	{
		INSTANCE = null;
	}
}
