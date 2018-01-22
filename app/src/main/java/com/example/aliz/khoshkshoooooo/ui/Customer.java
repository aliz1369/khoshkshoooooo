package com.example.aliz.khoshkshoooooo.ui;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.aliz.khoshkshoooooo.R;
import com.example.aliz.khoshkshoooooo.adapter.CustomerAdapter;
import com.example.aliz.khoshkshoooooo.controller.CustomerList;
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
 * Created by Al!Z on 9/3/2017.
 */

public class Customer extends AppCompatActivity{
    FloatingActionButton AddCustomer;
    SharedPreferences sharedPreferences;
    String auth;
    CustomerList customerList;
    List<CustomerList> customerLists = new ArrayList<>();
    RecyclerView CustomerDetail;
    RecyclerView.LayoutManager layoutManager;
    CustomerAdapter customerAdapter;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                .setDefaultFontPath("IRANYekanMobileBold.ttf")
                .setFontAttrId(R.attr.fontPath)
                .build()
        );
        super.onCreate(savedInstanceState);
        sharedPreferences = getSharedPreferences("loginInfo",MODE_PRIVATE);
        auth = sharedPreferences.getString("access_token","");
        setupUI();
        getCustomerList();
    }

    private void setupUI() {
        setContentView(R.layout.customer);
        CustomerDetail = (RecyclerView) findViewById(R.id.Customer_rvCustomerList);
        AddCustomer = (FloatingActionButton)findViewById(R.id.Customer_fbAddCustomer);
        AddCustomer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Customer.this,AddCustomer.class);
                startActivity(intent);
            }
        });
        layoutManager = new LinearLayoutManager(Customer.this);
    }
    public void getCustomerList() {
        final HttpCall httpGetAllCustomers = new HttpCall();
        httpGetAllCustomers.setMethodtype(HttpCall.POST);
        httpGetAllCustomers.setUrl("http://dry.dpark.ir/api/bill/CustomerList?query");
        HashMap<String,String> params = new HashMap<>();
        httpGetAllCustomers.setParams(params);
        httpGetAllCustomers.setAuthorization("Bearer "+ auth);
        new HttpUtility(){
            @Override
            public void onResponse(JSONObject response) {
                super.onResponse(response);
                try {
                    String Cus = response.getString("responseJSON");
                    JSONArray CustomerList = new JSONArray(Cus);
                    for(int i = 0;i< CustomerList.length();i++){
                        customerList = new CustomerList();
                        JSONObject Customer = CustomerList.getJSONObject(i);
                        customerList.setCustomerName(Customer.getString("first_name")+" "+Customer.getString("last_name"));
                        customerList.setCustomerPhoneNumber(Customer.getString("PhoneNumber"));
                        customerLists.add(customerList);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                udateList();
            }
                }.execute(httpGetAllCustomers);
    }
    public void udateList(){
        CustomerDetail.setLayoutManager(layoutManager);
        customerAdapter = new CustomerAdapter(customerLists);
        customerAdapter.notifyDataSetChanged();
        customerAdapter.setOnItemClickListener(new CustomerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                System.out.println(customerLists.get(position).getCustomerName());
                Intent intent = new Intent(Customer.this,ChoiceClothesKind.class);
                startActivity(intent);
            }
        });
        CustomerDetail.setAdapter(customerAdapter);
    }
}
