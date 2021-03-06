package com.example.aliz.khoshkshoooooo.server;

import android.os.AsyncTask;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Al!Z on 10/29/2017.
 */

public class HttpUtility extends AsyncTask<HttpCall, String, JSONObject> {
    private static final String UTF_8 = "UTF-8";
    @Override
    protected JSONObject doInBackground(HttpCall... params) {
        HttpURLConnection urlConnection = null;
        HttpCall httpCall = params[0];
        StringBuilder response = new StringBuilder();
        JSONObject httpResponse = new JSONObject();
        try{
            String dataParams = getDataString(httpCall.getParams(), httpCall.getMethodtype());
            URL url = new URL(httpCall.getMethodtype() == HttpCall.GET ? httpCall.getUrl() + dataParams : httpCall.getUrl());
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod(httpCall.getMethodtype() == HttpCall.GET ? "GET":"POST");
            urlConnection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            urlConnection.setRequestProperty("Authorization",httpCall.getAuthorization());
            urlConnection.setReadTimeout(10000);
            urlConnection.setConnectTimeout(15000);
            if(httpCall.getParams() != null && httpCall.getMethodtype() == HttpCall.POST){
                OutputStreamWriter writer = new OutputStreamWriter(urlConnection.getOutputStream());
                writer.write(dataParams);
                writer.flush();
            }
            int responseCode = urlConnection.getResponseCode();
            System.out.println("responseCode" + responseCode);
            httpResponse.put("responseCode" , String.valueOf(responseCode));
            if(responseCode == HttpURLConnection.HTTP_OK){
                String line ;
                BufferedReader br = new BufferedReader( new InputStreamReader(urlConnection.getInputStream()));
                while ((line = br.readLine()) != null){
                    response.append(line);
                }
            }
            else {
                String line ;
                BufferedReader br = new BufferedReader( new InputStreamReader(urlConnection.getErrorStream()));
                while ((line = br.readLine()) != null){
                    response.append(line);
                }
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        } finally {
            urlConnection.disconnect();
        }
        try {
            httpResponse.put("responseJSON",response.toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return httpResponse;
    }

    @Override
    protected void onPostExecute(JSONObject s) {
        super.onPostExecute(s);
        onResponse(s);

    }

    public void onResponse(JSONObject response){

    }

    private String getDataString(HashMap<String,String> params, int methodType) throws UnsupportedEncodingException {
        StringBuilder result = new StringBuilder();
        boolean isFirst = true;
        for(Map.Entry<String,String> entry : params.entrySet()){
            if (isFirst){
                isFirst = false;
                if(methodType == HttpCall.GET){
                    result.append("?");
                }
            }else{
                result.append("&");
            }
            result.append(URLEncoder.encode(entry.getKey(), UTF_8));
            result.append("=");
            result.append(URLEncoder.encode(entry.getValue(), UTF_8));
        }
        return result.toString();
    }
}