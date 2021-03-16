package com.dvm.evyap.thingworxrestconsumer.dvmexception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ConsumerError implements ExceptionStrategy{

    @Override
    public <T extends Exception> ResponseEntity<String> exceptionResponse(T e) {
        return ResponseEntity
                .status(HttpStatus.ACCEPTED)
                .header("Content-Type","application/json")
                .body("{\"status\":"+((ConsumerException) e).getCode()+",\"data\":[],\"response\":false,\"isKVKKConfirmed\":true}");
    }
}
