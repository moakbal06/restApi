package com.dvm.evyap.thingworxrestconsumer.utils;

import com.google.gson.Gson;

import java.lang.reflect.Type;

public class GenericGsonParser {


    public static <T> T ParseEntity(String input, Class<?> T){
        Gson gsonParser = new Gson();

       T  resObj = gsonParser.fromJson(input, (Type) T.getClass());

        return resObj;

    }
}
