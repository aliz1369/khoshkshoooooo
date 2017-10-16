package com.example.aliz.khoshkshoooooo.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import com.example.aliz.khoshkshoooooo.R;
import uk.co.chrisjenx.calligraphy.CalligraphyConfig;

/**
 * Created by Al!Z on 9/3/2017.
 */

public class Customer extends AppCompatActivity{
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
    }
}
