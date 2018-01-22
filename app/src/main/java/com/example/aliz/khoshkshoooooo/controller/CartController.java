package com.example.aliz.khoshkshoooooo.controller;

import android.app.Application;

import java.util.ArrayList;

/**
 * Created by AliZ on 1/19/2018.
 */

public class CartController extends Application {
    private ArrayList<CartList> cartLists = new ArrayList<>();

    public ArrayList<CartList> getCartLists() {
        return cartLists;
    }

    public void setCartLists(ArrayList<CartList> cartLists) {
        this.cartLists = cartLists;
    }
}
