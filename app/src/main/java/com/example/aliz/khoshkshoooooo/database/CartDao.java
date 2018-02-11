package com.example.aliz.khoshkshoooooo.database;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.aliz.khoshkshoooooo.controller.CartList;

import java.util.List;

/**
 * Created by AliZ on 12/10/17.
 */
@Dao
public interface CartDao{
    @Query("SELECT * FROM CartList")
    List<CartList> getAll();
    @Insert
    void insertAll(List<CartList> cartLists);

    @Query("DELETE FROM CartList")
    void nukeTable();

}
