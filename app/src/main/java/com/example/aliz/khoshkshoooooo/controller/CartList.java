package com.example.aliz.khoshkshoooooo.controller;

/**
 * Created by AliZ on 1/19/2018.
 */

public class CartList {
    private int stuff;
    private int color;
    private int orderprice;
    private int orderstatus;

    public CartList(int stuff, int color, int orderprice, int orderstatus) {
        this.stuff = stuff;
        this.color = color;
        this.orderprice = orderprice;
        this.orderstatus = orderstatus;
    }

    public int getStuff() {
        return stuff;
    }

    public int getColor() {
        return color;
    }

    public int getOrderprice() {
        return orderprice;
    }

    public int getOrderstatus() {
        return orderstatus;
    }
}
