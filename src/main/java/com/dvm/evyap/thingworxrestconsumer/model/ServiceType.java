package com.dvm.evyap.thingworxrestconsumer.model;

public enum ServiceType {

    SERVICE(1,"Services"),
    PROPERTY(2,"Property");

    private int i;
    private String service;

    ServiceType(int i, String service) {
        this.i=i;
        this.service=service;
    }

    public int getI() {
        return i;
    }

    public String getService() {
        return service;
    }
}
