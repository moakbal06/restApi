package com.dvm.evyap.thingworxrestconsumer.dvmexception;

import com.dvm.evyap.thingworxrestconsumer.utils.JSONResponseBuilder;
import org.json.JSONArray;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ParserError implements ExceptionStrategy {

    @Override
    public <T extends Exception> ResponseEntity<String> exceptionResponse(T e) {

        JSONResponseBuilder responseBuilder = JSONResponseBuilder.builder().response(false).data(new JSONArray()).status(((ParserException) e).getCode()).build();


        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .header("Content-Type", "application/json")
                .body(responseBuilder.getJSONResponse());
    }
}
