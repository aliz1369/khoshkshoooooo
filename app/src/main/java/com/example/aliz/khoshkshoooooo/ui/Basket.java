package com.example.aliz.khoshkshoooooo.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.aliz.khoshkshoooooo.R;
import com.example.aliz.khoshkshoooooo.database.App;

import uk.co.chrisjenx.calligraphy.CalligraphyConfig;

/**
 * Created by Al!Z on 9/3/2017.
 */

public class Basket extends AppCompatActivity {
    private Button toCustomerSelect;
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
        setContentView(R.layout.basket);
        toCustomerSelect = (Button)findViewById(R.id.Basket_btnChoiceCustomer);
        toCustomerSelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Basket.this,Customer.class);
                startActivity(intent);
            }
        });
        new Thread(new Runnable() {
            @Override
            public void run() {
                for(int i = 0 ;i < App.get().getDB().cartDao().getAll().size();i++){

                    System.out.println("DATAaaaaaaaaa" + App.get().getDB().cartDao().getAll().get(i).getStuff());

                }
            }
        }).start();}
}