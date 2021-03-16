package com.dvm.evyap.thingworxrestconsumer.dvmexception;

import com.dvm.evyap.thingworxrestconsumer.utils.JSONResponseBuilder;
import org.json.JSONArray;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;

public class HttpError implements ExceptionStrategy{

    @Override
    public <T extends Exception> ResponseEntity<String> exceptionResponse(T e) {

        JSONResponseBuilder responseBuilder = new JSONResponseBuilder();
        responseBuilder.setResponse(false);
        responseBuilder.setData(new JSONArray());

        if (((HttpClientErrorException) e).getStatusCode() == HttpStatus.UNAUTHORIZED) {

                responseBuilder.setStatus("Your Thingworx Credentials are invalid or You may not have permission to use this network resource(401 unauthorized)");

                return ResponseEntity
                        .status(((HttpClientErrorException) e).getStatusCode())
                        .header("Content-Type", "application/json")
                        .body(responseBuilder.getJSONResponse());
            } else {

                responseBuilder.setStatus(e.getMessage());

                return ResponseEntity
                        .status(((HttpClientErrorException) e).getStatusCode())
                        .header("Content-Type", "application/json")
                        .body(responseBuilder.getJSONResponse());
            }
        }
}
