package com.example.aliz.khoshkshoooooo.database;
import android.arch.persistence.room.TypeConverter;

import com.example.aliz.khoshkshoooooo.controller.ServiceList;
import com.example.aliz.khoshkshoooooo.controller.ServiceSelectedList;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

/**
 * Created by AliZ on 2/1/18.
 */

public class DataTypeConverter {
    @TypeConverter
    public static ArrayList<ServiceSelectedList> fromString(String value){
        Type listType = new TypeToken<ArrayList<ServiceSelectedList>>(){}.getType();
        return new Gson().fromJson(value,listType);
    }
    @TypeConverter
    public static String fromArrayList(ArrayList<ServiceSelectedList> serviceLists){
        Gson gson = new Gson();
        String json = gson.toJson(serviceLists);
        return json;
    }

}
