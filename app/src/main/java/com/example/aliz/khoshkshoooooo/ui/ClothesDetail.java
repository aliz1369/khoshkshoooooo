package com.example.aliz.khoshkshoooooo.ui;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;


import com.example.aliz.khoshkshoooooo.R;
import com.example.aliz.khoshkshoooooo.adapter.ServiceList;
import com.example.aliz.khoshkshoooooo.adapter.ServiceListAdapter;
import com.example.aliz.khoshkshoooooo.server.HttpCall;
import com.example.aliz.khoshkshoooooo.server.HttpUtility;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import uk.co.chrisjenx.calligraphy.CalligraphyConfig;

/**
 * Created by AliZ on 1/31/2017.
 */

public class ClothesDetail extends AppCompatActivity {
    private Button addToBasket;
    private RecyclerView services;
    String KindName;
    SharedPreferences sharedPreferences;
    String auth;
    ServiceList serviceList;
    List<ServiceList> serviceLists = new ArrayList<>();
    RecyclerView.LayoutManager layoutManager;
    ServiceListAdapter adapter;
    EditText Number;
    ImageButton Increase,Decrease;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                .setDefaultFontPath("IRANYekanMobileBold.ttf")
                .setFontAttrId(R.attr.fontPath)
                .build()
        );
        super.onCreate(savedInstanceState);
        Bundle extras = getIntent().getExtras();
        if(extras!=null){
            KindName = extras.getString("KindName");
        }
        sharedPreferences = getSharedPreferences("loginInfo",MODE_PRIVATE);
        auth = sharedPreferences.getString("access_token","");
        setupUI();
        getServiceList();

    }

    private void getServiceList() {
        HttpCall httpGetAllServices = new HttpCall();
        httpGetAllServices.setMethodtype(HttpCall.GET);
        httpGetAllServices.setUrl("http://dry.dpark.ir/api/bill/GetOfficeServices");
        HashMap<String,String> params = new HashMap<>();
        httpGetAllServices.setParams(params);
        httpGetAllServices.setAuthorization("Bearer "+ auth);
        new HttpUtility(){
            @Override
            public void onResponse(JSONObject response) {
                super.onResponse(response);
                try {
                    JSONArray Services = new JSONArray(response);
                    for(int i = 0;i<Services.length();i++){
                        serviceList = new ServiceList();
                        JSONObject jsonObject = Services.getJSONObject(i);
                        serviceList.setServiceId(jsonObject.getString("service_id"));
                        serviceList.setServiceName(jsonObject.getString("service_name"));
                        serviceLists.add(serviceList);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                updateServiceListView();
                System.out.println("kindddddd"+response);
            }
        }.execute(httpGetAllServices);
    }

    private void updateServiceListView() {
        services.setLayoutManager(layoutManager);
        
        adapter = new ServiceListAdapter(serviceLists);
        adapter.setItemClickListener(new ServiceListAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position, String ServiceName) {

                
            }
        });
        adapter.notifyDataSetChanged();
        services.setAdapter(adapter);
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
        services = (RecyclerView) findViewById(R.id.ClothesDetail_rvServices);
        layoutManager = new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false);
        Number = (EditText) findViewById(R.id.ClothesDetail_etNumber);
        Increase = (ImageButton) findViewById(R.id.ClothesDetail_ibIncrease);
        Decrease = (ImageButton) findViewById(R.id.ClothesDetail_ibDecrease);
        Number.setText("0");
        Increase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int num = Integer.parseInt(Number.getText().toString());
                num = num + 1;
                Number.setText(num+"");
            }
        });
        Decrease.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int num = Integer.parseInt(Number.getText().toString());
                num = num -1;
                Number.setText(num+"");
            }
        });
    }

}
