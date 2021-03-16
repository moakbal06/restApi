package com.dvm.evyap.thingworxrestconsumer.model;

public enum EntityType {

    THING(1,"Things"),
    THING_TEMPLATE(2,"ThingTemplate"),
    THING_SHAPE(3,"ThingShape"),
    USERS(4,"Users"),
    RUNTIME(5,"Runtime");


    private int id;
    private String type;

    EntityType(int id, String type) {
        this.id=id;
        this.type=type;
    }

    public int getId() {
        return id;
    }

    public String getType() {
        return type;
    }
}
