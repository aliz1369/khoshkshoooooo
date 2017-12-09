package com.example.aliz.khoshkshoooooo.server;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import com.example.aliz.khoshkshoooooo.ui.ClothesDetail;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Al!Z on 10/30/2017.
 */

public class RequestMethod {
    public static void Login(String Username,String Password){
        HttpCall httpLogin = new HttpCall();
        httpLogin.setMethodtype(HttpCall.POST);
        httpLogin.setUrl("http://dry.dpark.ir/Token");
        HashMap<String,String> params = new HashMap<>();
        params.put("grant_type","password");
        params.put("username",Username);
        params.put("password",Password);
        httpLogin.setParams(params);
        new HttpUtility(){
            @Override
            public void onResponse(String response) {
                super.onResponse(response);
                try {
                    JSONObject loginInfo = new JSONObject(response);
                    if(loginInfo.has("access_token")){
                    String access_token = loginInfo.getString("access_token");
                    String expire = loginInfo.getString(".expires");
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }.execute(httpLogin);
    }
    public static void GetAllService(String auth){
        HttpCall httpGetAllServices = new HttpCall();
        httpGetAllServices.setMethodtype(HttpCall.GET);
        httpGetAllServices.setUrl("http://dry.dpark.ir/api/bill/GetOfficeServices");
        HashMap<String,String> params = new HashMap<>();
        httpGetAllServices.setParams(params);
        httpGetAllServices.setAuthorization("Bearer "+ auth);
        new HttpUtility(){
            @Override
            public void onResponse(String response) {
                super.onResponse(response);
            }
        }.execute(httpGetAllServices);

    }
}
