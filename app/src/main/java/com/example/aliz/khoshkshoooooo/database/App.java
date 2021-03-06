package com.example.aliz.khoshkshoooooo.database;

import android.app.Application;
import android.arch.persistence.room.Room;
import com.example.aliz.khoshkshoooooo.database.MyDatabase;
/**
 * Created by AliZ on 1/23/18.
 */

public class App extends Application {

    public static App INSTANCE;
    private static final String DATABASE_NAME = "CartDB";

    private MyDatabase database;


    @Override
    public void onCreate() {
        super.onCreate();

        // create database
        database = Room.databaseBuilder(getApplicationContext(), MyDatabase.class, DATABASE_NAME)
                .build();

        INSTANCE = this;
    }
    public static App get() {
        return INSTANCE;
    }

    public MyDatabase getDB() {
        return database;
    }
}
