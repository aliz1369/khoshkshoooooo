package com.example.aliz.khoshkshoooooo;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.kofigyan.stateprogressbar.StateProgressBar;

import uk.co.chrisjenx.calligraphy.CalligraphyConfig;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

/**
 * Created by AliZ on 1/31/2017.
 */

public class Main extends AppCompatActivity {
    String[] descriptionData = {"Details", "Status", "Photo", "Confirm"};
    ImageView imageView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                .setDefaultFontPath("IRANYekanMobileBold.ttf")
                .setFontAttrId(R.attr.fontPath)
                .build()
        );
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        StateProgressBar stateProgressBar = (StateProgressBar) findViewById(R.id.your_state_progress_bar_id);
        stateProgressBar.setStateDescriptionData(descriptionData);
        imageView = (ImageView)findViewById(R.id.imageView2);

        stateProgressBar.setCurrentStateNumber(StateProgressBar.StateNumber.THREE);
        System.out.println(stateProgressBar.getCurrentStateNumber());
        switch (stateProgressBar.getCurrentStateNumber()){
            case(1):
                imageView.setImageResource(R.drawable.ic_basket_filled);
                break;
            case(2):
                imageView.setImageResource(R.drawable.ic_truckwait);
                break;
            case(3):
                imageView.setImageResource(R.drawable.ic_recieve_sms);
                break;
            case(4):
                imageView.setImageResource(R.drawable.ic_mobile);
        }
    }
    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }
}
