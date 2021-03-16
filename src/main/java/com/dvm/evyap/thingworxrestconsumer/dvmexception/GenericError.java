package com.dvm.evyap.thingworxrestconsumer.dvmexception;

import com.dvm.evyap.thingworxrestconsumer.utils.JSONResponseBuilder;
import org.json.JSONArray;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class GenericError implements ExceptionStrategy{

    @Override
    public <T extends Exception> ResponseEntity<String> exceptionResponse(T e) {

        JSONResponseBuilder responseBuilder = new JSONResponseBuilder();
        responseBuilder.setResponse(false);
        responseBuilder.setData(new JSONArray());
        responseBuilder.setStatus(e.getMessage()==null?"Unknown message":e.getMessage());

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(responseBuilder.getJSONResponse());
    }
}
