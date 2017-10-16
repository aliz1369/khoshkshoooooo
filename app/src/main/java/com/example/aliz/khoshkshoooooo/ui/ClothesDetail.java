package com.example.aliz.khoshkshoooooo.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;


import com.example.aliz.khoshkshoooooo.R;

import uk.co.chrisjenx.calligraphy.CalligraphyConfig;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

/**
 * Created by AliZ on 1/31/2017.
 */

public class ClothesDetail extends AppCompatActivity {
    private Button addToBasket;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                .setDefaultFontPath("IRANYekanMobileBold.ttf")
                .setFontAttrId(R.attr.fontPath)
                .build()
        );
        super.onCreate(savedInstanceState);
        setupUI();

    }

    private void setupUI() {
        setContentView(R.layout.clothes_detail);
        addToBasket = (Button)findViewById(R.id.ClothesDetail_btAddToBasket);
        addToBasket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ClothesDetail.this,Basket.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }
}
