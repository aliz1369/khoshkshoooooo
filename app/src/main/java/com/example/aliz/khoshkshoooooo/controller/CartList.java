package com.example.aliz.khoshkshoooooo.controller;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

/**
 * Created by AliZ on 1/19/2018.
 */
@Entity
public class CartList {
    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "stuff")
    private String stuff;

    @ColumnInfo(name = "color")
    private String color;

    @ColumnInfo(name = "price")
    private String orderprice;

    @ColumnInfo(name = "status")
    private String orderstatus;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStuff() {
        return stuff;
    }

    public void setStuff(String stuff) {
        this.stuff = stuff;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getOrderprice() {
        return orderprice;
    }

    public void setOrderprice(String orderprice) {
        this.orderprice = orderprice;
    }

    public String getOrderstatus() {
        return orderstatus;
    }

    public void setOrderstatus(String orderstatus) {
        this.orderstatus = orderstatus;
    }
}
