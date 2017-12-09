package com.example.aliz.khoshkshoooooo.ui;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.example.aliz.khoshkshoooooo.R;

import uk.co.chrisjenx.calligraphy.CalligraphyConfig;

/**
 * Created by Al!Z on 11/1/2017.
 */

public class Splash extends AppCompatActivity {
    SharedPreferences sharedPreferences;
    private static int SPLASH_TIME_OUT = 5000;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                .setDefaultFontPath("IRANYekanMobileRegular.ttf")
                .setFontAttrId(R.attr.fontPath)
                .build()
        );
        setupUI();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
               /* sharedPreferences = getSharedPreferences("loginInfo",MODE_PRIVATE);
                if(sharedPreferences.contains("access_token")){
                    Intent intent = new Intent(Splash.this,ChoiceClothesKind.class);
                    startActivity(intent);
                    finish();
                }else {*/
                    Intent intent = new Intent(Splash.this,Login.class);
                    startActivity(intent);
                    finish();
               /* }*/
            }
        },SPLASH_TIME_OUT);
    }

    private void setupUI() {
        setContentView(R.layout.splash);
    }
}
