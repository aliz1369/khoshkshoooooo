package com.example.aliz.khoshkshoooooo.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import com.example.aliz.khoshkshoooooo.R;
import com.example.aliz.khoshkshoooooo.adapter.BasketAdapter;
import com.example.aliz.khoshkshoooooo.controller.BasketList;
import com.example.aliz.khoshkshoooooo.database.App;

import java.util.ArrayList;
import java.util.List;

import uk.co.chrisjenx.calligraphy.CalligraphyConfig;

/**
 * Created by Al!Z on 9/3/2017.
 */

public class Basket extends AppCompatActivity {
    private Button toCustomerSelect;
    private RecyclerView basketList;
    private List<BasketList> basketLists  = new ArrayList<>();
    RecyclerView.LayoutManager layoutManager;
    BasketAdapter adapter;
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
        basketList = (RecyclerView) findViewById(R.id.Basket_rvBasket);
        layoutManager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        new Thread(new Runnable() {
            @Override
            public void run() {
                for(int i = 0 ;i < App.get().getDB().cartDao().getAll().size();i++){
                    BasketList basketList = new BasketList();
                    basketList.setPrice(App.get().getDB().cartDao().getAll().get(i).getOrderprice());
                    basketList.setAmount(App.get().getDB().cartDao().getAll().get(i).getStuffamount());
                    basketList.setType(App.get().getDB().cartDao().getAll().get(i).getStuffName());
                    basketList.setDetail(App.get().getDB().cartDao().getAll().get(i).getDetail());
                    basketLists.add(basketList);
                }
            }
        }).start();
        updateBasketList();
    }
    private void updateBasketList() {
        basketList.setLayoutManager(layoutManager);
        adapter = new BasketAdapter(basketLists);
        adapter.notifyDataSetChanged();
        basketList.setAdapter(adapter);

    }

}