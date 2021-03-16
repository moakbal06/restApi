package com.dvm.evyap.thingworxrestconsumer.utils;

import com.dvm.evyap.thingworxrestconsumer.dvmexception.*;
import com.dvm.evyap.thingworxrestconsumer.dvmexception.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;

public class ExceptionHandler {

    public static ResponseEntity<String> handleException(Exception e) {
        e.printStackTrace();
        ExceptionBuilder exceptionBuilder ;
        if (e instanceof HttpClientErrorException){
            exceptionBuilder = new ExceptionBuilder(new HttpError());
            return exceptionBuilder.exceptionResponse(e);
        }
        else if( e instanceof ConsumerException){
            exceptionBuilder = new ExceptionBuilder(new ConsumerError());
            return exceptionBuilder.exceptionResponse(e);
        }
        else if( e instanceof ParserException){
            exceptionBuilder = new ExceptionBuilder(new ParserError());
            return exceptionBuilder.exceptionResponse(e);
        }
        else {
            exceptionBuilder = new ExceptionBuilder(new GenericError());
            return exceptionBuilder.exceptionResponse(e);
        }
    }
}
