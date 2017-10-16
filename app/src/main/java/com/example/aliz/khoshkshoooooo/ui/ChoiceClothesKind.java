package com.example.aliz.khoshkshoooooo.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.example.aliz.khoshkshoooooo.R;

import uk.co.chrisjenx.calligraphy.CalligraphyConfig;

/**
 * Created by Al!Z on 10/14/2017.
 */

public class ChoiceClothesKind extends AppCompatActivity {
    private ImageButton Basket;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                .setDefaultFontPath("IRANYekanMobileBold.ttf")
                .setFontAttrId(R.attr.fontPath)
                .build()
        );
        super.onCreate(savedInstanceState);
        setupUi();
    }

    private void setupUi() {
        setContentView(R.layout.choice_clothes_kind);
        Basket = (ImageButton) findViewById(R.id.ClothesKind_btnToBasket);
        Basket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ChoiceClothesKind.this,Basket.class);
                startActivity(intent);
            }
        });

    }
}
