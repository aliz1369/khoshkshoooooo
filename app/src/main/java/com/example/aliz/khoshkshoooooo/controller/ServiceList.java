package com.example.aliz.khoshkshoooooo.controller;

/**
 * Created by Al!Z on 11/7/2017.
 */

public class ServiceList {
    String ServiceName;
    String ServiceId;
    boolean ServiceSelected = false;
    public String getServiceName() {
        return ServiceName;
    }

    public void setServiceName(String serviceName) {
        ServiceName = serviceName;
    }

    public String getServiceId() {
        return ServiceId;
    }

    public void setServiceId(String serviceId) {
        ServiceId = serviceId;
    }

    public boolean isServiceSelected() {
        return ServiceSelected;
    }

    public void setServiceSelected(boolean serviceSelected) {
        ServiceSelected = serviceSelected;
    }
}
