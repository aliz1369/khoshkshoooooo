package com.example.aliz.khoshkshoooooo.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.aliz.khoshkshoooooo.R;
import uk.co.chrisjenx.calligraphy.CalligraphyConfig;

/**
 * Created by Al!Z on 9/3/2017.
 */

public class Customer extends AppCompatActivity{
    FloatingActionButton AddCustomer;
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
        setContentView(R.layout.customer);
        AddCustomer = (FloatingActionButton)findViewById(R.id.Customer_fbAddCustomer);
        AddCustomer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Customer.this,AddCustomer.class);
                startActivity(intent);
            }
        });
    }
}
