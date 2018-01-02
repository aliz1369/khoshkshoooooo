package com.example.aliz.khoshkshoooooo.ui;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.aliz.khoshkshoooooo.R;
import com.example.aliz.khoshkshoooooo.server.HttpCall;
import com.example.aliz.khoshkshoooooo.server.HttpUtility;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

import uk.co.chrisjenx.calligraphy.CalligraphyConfig;

/**
 * Created by AliZ on 12/18/2017.
 */

public class AddCustomer extends AppCompatActivity {
    SharedPreferences sharedPreferences;
    String auth;
    Button AddCustomer,Cancel;
    EditText Fname,Lname,Email,Address,PhoneNumber;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                .setDefaultFontPath("IRANYekanMobileBold.ttf")
                .setFontAttrId(R.attr.fontPath)
                .build()
        );
        sharedPreferences = getSharedPreferences("loginInfo",MODE_PRIVATE);
        auth = sharedPreferences.getString("access_token","");
        setupUI();
    }

    private void setupUI() {
        setContentView(R.layout.add_customer);
        Cancel = (Button)findViewById(R.id.AddCustomer_btCancel);
        AddCustomer = (Button) findViewById(R.id.AddCustomer_btSave);
        Fname = (EditText) findViewById(R.id.AddCustomer_etFName);
        Lname = (EditText) findViewById(R.id.AddCustomer_etLName);
       // Email = (EditText) findViewById(R.id.AddCustomer_etEmail);
        Address = (EditText) findViewById(R.id.AddCustomer_etAddress);
        PhoneNumber = (EditText) findViewById(R.id.AddCustomer_etMobileNumber);
        AddCustomer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String FirstName = Fname.getText().toString();
                String LastName = Lname.getText().toString();
               // String email = Email.getText().toString();
                String address = Address.getText().toString();
                String phoneNumber = PhoneNumber.getText().toString();
                HttpCall httpAddCustomer = new HttpCall();
                httpAddCustomer.setMethodtype(HttpCall.POST);
                httpAddCustomer.setUrl("http://dry.dpark.ir/api/Account/AddCustomer");
                HashMap<String,String> params = new HashMap<>();
                params.put("firstname",FirstName);
                params.put("lastname",LastName);
                params.put("PhoneNumber",phoneNumber);
                params.put("address",address);
                httpAddCustomer.setParams(params);
                httpAddCustomer.setAuthorization("Bearer "+ auth);
                new HttpUtility(){
                    @Override
                    public void onResponse(String response) {
                        super.onResponse(response);
                        System.out.println("outtttttt"+response);
                    }
                }.execute(httpAddCustomer);
        }
        });
            }
}
