package com.example.aliz.khoshkshoooooo.controller;

/**
 * Created by Al!Z on 9/16/2017.
 */

public class BillList {
    public String Type;
    public String Detail;
    public String Amount;
    public String Price;
    public boolean Wash;
    public boolean Iron;
    public boolean Coloring;
    public boolean Dry;
    public boolean StainCleaning;

    public String getType() {
        return Type;
    }

    public void setType(String type) {
        Type = type;
    }

    public String getDetail() {
        return Detail;
    }

    public void setDetail(String detail) {
        Detail = detail;
    }

    public String getAmount() {
        return Amount;
    }

    public void setAmount(String amount) {
        Amount = amount;
    }

    public String getPrice() {
        return Price;
    }

    public void setPrice(String price) {
        Price = price;
    }

    public boolean isWash() {
        return Wash;
    }

    public void setWash(boolean wash) {
        Wash = wash;
    }

    public boolean isIron() {
        return Iron;
    }

    public void setIron(boolean iron) {
        Iron = iron;
    }

    public boolean isColoring() {
        return Coloring;
    }

    public void setColoring(boolean coloring) {
        Coloring = coloring;
    }

    public boolean isDry() {
        return Dry;
    }

    public void setDry(boolean dry) {
        Dry = dry;
    }

    public boolean isStainCleaning() {
        return StainCleaning;
    }

    public void setStainCleaning(boolean stainCleaning) {
        StainCleaning = stainCleaning;
    }
}
