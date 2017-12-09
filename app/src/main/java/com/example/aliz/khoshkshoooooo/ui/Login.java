package com.example.aliz.khoshkshoooooo.ui;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.aliz.khoshkshoooooo.R;
import com.example.aliz.khoshkshoooooo.server.HttpCall;
import com.example.aliz.khoshkshoooooo.server.HttpUtility;
import com.example.aliz.khoshkshoooooo.server.RequestMethod;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

import uk.co.chrisjenx.calligraphy.CalligraphyConfig;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

/**
 * Created by AliZ on 1/18/2017.
 */

public class Login extends AppCompatActivity {
    Button next;
    EditText userName,password;
    SharedPreferences sharedPreferences;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                .setDefaultFontPath("IRANYekanMobileRegular.ttf")
                .setFontAttrId(R.attr.fontPath)
                .build()
        );
        setupUI();
        sharedPreferences = getSharedPreferences("loginInfo",Context.MODE_PRIVATE);
    }

    private void setupUI() {
        setContentView(R.layout.login);
        userName = (EditText)findViewById(R.id.login_etUsername);
        password = (EditText)findViewById(R.id.login_etPassword);
        next = (Button)findViewById(R.id.login_button);
       // RequestMethod.Login("kamraneh","123456");
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = userName.getText().toString();
                String passdword = password.getText().toString();
                //RequestMethod.Login(username,passdword);
                HttpCall httpLogin = new HttpCall();
                httpLogin.setMethodtype(HttpCall.POST);
                httpLogin.setUrl("http://dry.dpark.ir/Token");
                HashMap<String,String> params = new HashMap<>();
                params.put("grant_type","password");
                params.put("username",username);
                params.put("password",passdword);
                httpLogin.setParams(params);
                new HttpUtility(){
                    @Override
                    public void onResponse(String response) {
                        super.onResponse(response);
                        try {
                            JSONObject loginInfo = new JSONObject(response);
                            if(loginInfo.has("access_token")){
                                String access_token = loginInfo.getString("access_token");
                                String expire = loginInfo.getString(".expires");
                                SharedPreferences.Editor editor = sharedPreferences.edit();
                                editor.putString("access_token",access_token);
                                editor.putString("expire_date",expire);
                                editor.apply();
                                Intent intent = new Intent(Login.this,ChoiceClothesKind.class);
                                startActivity(intent);
                            }
                            else {
                                Toast.makeText(Login.this,"نام کاربری یا رمز عبور اشتباه می باشد.",Toast.LENGTH_LONG);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }.execute(httpLogin);
            }
        });
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }
}
