package com.example.aliz.khoshkshoooooo.ui;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.Image;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.aliz.khoshkshoooooo.R;
import com.example.aliz.khoshkshoooooo.adapter.ClothesKindAdapter;
import com.example.aliz.khoshkshoooooo.controller.ClothesKindList;
import com.example.aliz.khoshkshoooooo.database.App;
import com.example.aliz.khoshkshoooooo.server.HttpCall;
import com.example.aliz.khoshkshoooooo.server.HttpUtility;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import uk.co.chrisjenx.calligraphy.CalligraphyConfig;


public class ChoiceClothesKind extends AppCompatActivity {
    SharedPreferences sharedPreferences;
    String auth;
    ClothesKindList kindList;
    RecyclerView KindList;
    List<ClothesKindList> clothesKindLists = new ArrayList<>();
    RecyclerView.LayoutManager layoutManager;
    private TextView basketSize;
    private int BasketSize;
    ImageButton basket;
    ClothesKindAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                .setDefaultFontPath("IRANYekanMobileBold.ttf")
                .setFontAttrId(R.attr.fontPath)
                .build()
        );
        super.onCreate(savedInstanceState);
        sharedPreferences = getSharedPreferences("loginInfo",MODE_PRIVATE);
        auth = sharedPreferences.getString("access_token","");
        setupUi();
        getData();
    }

    private void updateListView() {

        KindList.setLayoutManager(layoutManager);
        adapter = new ClothesKindAdapter(clothesKindLists);
        adapter.notifyDataSetChanged();
        adapter.setItemClickListener(new ClothesKindAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position, String KindName) {
                Intent intent = new Intent(ChoiceClothesKind.this,ClothesDetail.class);
                intent.putExtra("KindId",clothesKindLists.get(position).getKindID());
                intent.putExtra("KindName",clothesKindLists.get(position).getKindName());
                startActivity(intent);
            }
        });
        KindList.setAdapter(adapter);

    }

    @SuppressLint("StaticFieldLeak")
    private void getData() {
        HttpCall httpGetAllServices = new HttpCall();
        httpGetAllServices.setMethodtype(HttpCall.GET);
        httpGetAllServices.setUrl("http://dry.dpark.ir/api/bill/GetOfficeStaffs");
        HashMap<String,String> params = new HashMap<>();
        httpGetAllServices.setParams(params);
        httpGetAllServices.setAuthorization("Bearer "+ auth);
        new HttpUtility(){
            @Override
            public void onResponse(JSONObject response) {
                super.onResponse(response);
                try {
                    String ClothesKind = response.getString("responseJSON");
                    JSONArray Services = new JSONArray(ClothesKind);
                    for(int i = 0;i<Services.length();i++){
                        kindList = new ClothesKindList();
                        JSONObject jsonObject = Services.getJSONObject(i);
                        kindList.setKindID(jsonObject.getString("id"));
                        kindList.setKindName(jsonObject.getString("name"));
                        clothesKindLists.add(kindList);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                updateListView();
            }
        }.execute(httpGetAllServices);
    }

    private void setupUi() {
        setContentView(R.layout.choice_clothes_kind);
        basket = (ImageButton) findViewById(R.id.ClothesKind_btnToBasket);
        basketSize = (TextView) findViewById(R.id.ClothesKind_tvBasketSize);
        new Thread(new Runnable() {
            @Override
            public void run() {
                BasketSize = App.get().getDB().cartDao().getAll().size();
            }
        }).start();
        basketSize.setText(""+BasketSize);
        basketSize.setVisibility(View.INVISIBLE);
        if(BasketSize>0){
            basket.setClickable(true);
            basketSize.setText(""+BasketSize);
            basketSize.setVisibility(View.VISIBLE);
        }
        basket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ChoiceClothesKind.this,Basket.class);
                startActivity(intent);
            }
        });
        KindList = (RecyclerView) findViewById(R.id.ClothesKind_rvLists);
        layoutManager =  new LinearLayoutManager(ChoiceClothesKind.this);

    }
}
