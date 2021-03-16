package com.dvm.evyap.thingworxrestconsumer.dvmexception;

import org.springframework.http.ResponseEntity;

public interface ExceptionStrategy {

    <T extends Exception> ResponseEntity<String>  exceptionResponse(T e);
}
