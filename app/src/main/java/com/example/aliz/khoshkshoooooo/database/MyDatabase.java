package com.example.aliz.khoshkshoooooo.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverters;

import com.example.aliz.khoshkshoooooo.controller.CartList;

/**
 * Created by AliZ on 1/23/18.
 */
@Database(entities = {CartList.class},version = 1)
@TypeConverters({DataTypeConverter.class})
public abstract class MyDatabase extends RoomDatabase {
    public abstract CartDao cartDao();
}
