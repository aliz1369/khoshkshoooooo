package com.example.aliz.khoshkshoooooo.ui;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.view.View;

import com.example.aliz.khoshkshoooooo.R;
import com.example.aliz.khoshkshoooooo.database.App;
import com.example.aliz.khoshkshoooooo.server.HttpCall;

import uk.co.chrisjenx.calligraphy.CalligraphyConfig;

/**
 * Created by AliZ on 12/17/2017.
 */

public class MainPage extends AppCompatActivity {
    SharedPreferences sharedPreferences;
    String auth;
    CardView RegisterBill;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                .setDefaultFontPath("IRANYekanMobileRegular.ttf")
                .setFontAttrId(R.attr.fontPath)
                .build()
        );
        sharedPreferences = getSharedPreferences("loginInfo",MODE_PRIVATE);
        auth = sharedPreferences.getString("access_token","");
        new Thread(new Runnable() {
            @Override
            public void run() {
                App.get().getDB().cartDao().nukeTable();
            }
        }).start();
        setupUI();
        getData();
    }


    private void setupUI() {
    setContentView(R.layout.mainpage);
    RegisterBill = (CardView) findViewById(R.id.MainPage_cvRegisterBill);
    RegisterBill.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent = new Intent(MainPage.this,Customer.class);
            startActivity(intent);
        }
    });
    }


    private void getData() {

    }
}
