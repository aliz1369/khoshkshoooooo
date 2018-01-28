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
import android.widget.RadioButton;


import com.example.aliz.khoshkshoooooo.R;
import com.example.aliz.khoshkshoooooo.controller.CartList;
import com.example.aliz.khoshkshoooooo.controller.ServiceList;
import com.example.aliz.khoshkshoooooo.adapter.ServiceListAdapter;
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

/**
 * Created by AliZ on 1/31/2017.
 */

public class ClothesDetail extends AppCompatActivity {
    private Button addToBasket,updatePrice;
    private RecyclerView services;
    String KindId;
    SharedPreferences sharedPreferences;
    String auth;
    ServiceList serviceList;
    List<ServiceList> serviceLists = new ArrayList<>();
    RecyclerView.LayoutManager layoutManager;
    ServiceListAdapter adapter;
    EditText Number;
    ImageButton Increase,Decrease;
    String serviceId;
    String clothesColor;
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
            KindId = extras.getString("KindId");
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
                    String Service = response.getString("responseJSON");
                    JSONArray Services = new JSONArray(Service);
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
                serviceId = serviceLists.get(position).getServiceId();
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
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        List<CartList> lists = new ArrayList<>();
                        CartList cartList = new CartList();
                        cartList.setStuff(KindId);
                        cartList.setColor(clothesColor);
                        cartList.setOrderprice(" ");
                        cartList.setOrderstatus("0");
                        lists.add(cartList);
                        App.get().getDB().cartDao().insertAll(lists);
                        Intent intent = new Intent(ClothesDetail.this,Basket.class);
                        startActivity(intent);
                    }
                }).start();
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
        updatePrice = (Button) findViewById(R.id.ClothesDetail_btnUpdatePrice);
        updatePrice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updatePrices();
            }
        });

    }

    private void updatePrices() {
        HttpCall httpUpdatePrice = new HttpCall();
        httpUpdatePrice.setMethodtype(HttpCall.POST);
        httpUpdatePrice.setUrl("http://dry.dpark.ir/api/bill/GetPrice");
        HashMap<String,String> params = new HashMap<>();
        params.put("staff_id",KindId);
        params.put("color_id",clothesColor);
        params.put("staff_count",Number.getText().toString());
        params.put("service_id",serviceId);
    }
    public void ChoiceClothesColor(View view){
        boolean Checked =((RadioButton) view).isChecked();
        switch (view.getId()){
            case R.id.ClothesDetail_rbWhite:
                if(Checked)
                    clothesColor = "1";
                break;
            case R.id.ClothesDetail_rbBlack:
                if(Checked)
                    clothesColor = "2";
                break;
            case R.id.ClothesDetail_rbColor:
                if(Checked)
                    clothesColor = "3";
                break;

        }
    }

}
