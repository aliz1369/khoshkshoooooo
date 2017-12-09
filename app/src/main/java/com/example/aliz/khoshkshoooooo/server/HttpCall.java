package com.example.aliz.khoshkshoooooo.server;

import java.util.HashMap;

/**
 * Created by Al!Z on 11/1/2017.
 */

public class HttpCall {
    public static final int GET = 1;
    public static final int POST = 2;

    private String url;
    private int methodtype;
    private String authorization;
    private HashMap<String,String> params ;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getMethodtype() {
        return methodtype;
    }

    public void setMethodtype(int methodtype) {
        this.methodtype = methodtype;
    }

    public HashMap<String, String> getParams() {
        return params;
    }

    public void setParams(HashMap<String, String> params) {
        this.params = params;
    }

    public String getAuthorization() {
        return authorization;
    }

    public void setAuthorization(String authorization) {
        this.authorization = authorization;
    }
}
