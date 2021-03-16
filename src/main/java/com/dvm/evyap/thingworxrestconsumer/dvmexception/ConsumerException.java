package com.dvm.evyap.thingworxrestconsumer.dvmexception;

import lombok.Data;

@Data
public class ConsumerException extends Exception{

    private Exception ex;
    private String code;
    private static final String CONFIGURATION_URL = "\"Application properties thingworx.url must be valid\"";
    private static final String DATA_EMPTY = "\"Response has no data\"";
    private static final String APP_KEY_EMPTY = "\"AppKey should not be empty.AppKey missing. Please, add an authorization token at the 'AppKey' HTTP header.\"";

    public ConsumerException(Exception ex,String code) {
        this.ex = ex;
        this.code = code;
    }

    public static ConsumerException urlEmpty() {
        return new ConsumerException(new Exception(CONFIGURATION_URL),CONFIGURATION_URL);
    }
    public static ConsumerException dataEmpty()  {
        return new ConsumerException(new Exception(DATA_EMPTY),DATA_EMPTY);
    }

    public static Exception appKeyEmtpy() {
        return new ConsumerException(new Exception(APP_KEY_EMPTY),APP_KEY_EMPTY);
    }
}
