package com.example.aliz.khoshkshoooooo.controller;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import java.util.ArrayList;

/**
 * Created by AliZ on 1/19/2018.
 */
@Entity
public class CartList {
    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "stuffid")
    private String stuffId;

    @ColumnInfo(name = "stuffname")
    private String stuffName;

    @ColumnInfo(name = "stuffamount")
    private String stuffamount;

    @ColumnInfo(name = "service")
    private ArrayList<ServiceSelectedList> serviceList = new ArrayList<>();

    @ColumnInfo(name = "color")
    private String color;

    @ColumnInfo(name = "price")
    private String orderprice;

    @ColumnInfo(name = "status")
    private String orderstatus;

    @ColumnInfo(name = "detail")
    private String detail;
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStuffId() {
        return stuffId;
    }

    public void setStuffId(String stuffId) {
        this.stuffId = stuffId;
    }

    public String getStuffName() {
        return stuffName;
    }

    public void setStuffName(String stuffName) {
        this.stuffName = stuffName;
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

    public ArrayList<ServiceSelectedList> getServiceList() {
        return serviceList;
    }

    public void setServiceList(ArrayList<ServiceSelectedList> serviceList) {
        this.serviceList = serviceList;
    }

    public String getStuffamount() {
        return stuffamount;
    }

    public void setStuffamount(String stuffamount) {
        this.stuffamount = stuffamount;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }
}
