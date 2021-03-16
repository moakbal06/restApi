package com.dvm.evyap.thingworxrestconsumer.dvmexception;

import org.springframework.http.ResponseEntity;

public class ExceptionBuilder {

    private ExceptionStrategy strategy;

    public ExceptionBuilder(ExceptionStrategy strategy){
        this.strategy=strategy;
    }

    public  <T extends Exception> ResponseEntity<String> exceptionResponse(T e){

      return   strategy.exceptionResponse(e);
    }
}
