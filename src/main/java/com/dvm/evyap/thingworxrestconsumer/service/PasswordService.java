package com.dvm.evyap.thingworxrestconsumer.service;

import com.dvm.evyap.thingworxrestconsumer.dvmexception.ConsumerException;
import com.dvm.evyap.thingworxrestconsumer.dvmexception.ParserException;
import com.dvm.evyap.thingworxrestconsumer.model.ThingworxInfo;
import com.dvm.evyap.thingworxrestconsumer.utils.ExceptionHandler;
import com.dvm.evyap.thingworxrestconsumer.utils.JSONResponseBuilder;
import com.dvm.evyap.thingworxrestconsumer.model.ServiceInfo;
import com.dvm.evyap.thingworxrestconsumer.utils.Parameters;
import com.dvm.evyap.thingworxrestconsumer.utils.ResponseParser;
import org.json.JSONArray;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

@Service
public class PasswordService {

    RestTemplate restTemplate;

    ThingworxInfo thingworxInfo;

    HttpHeaders headers;

    Parameters parameters;


    public PasswordService(ThingworxInfo thingworxInfo, RestTemplate template, Parameters parameters) {
        this.parameters=parameters;
        this.thingworxInfo=thingworxInfo;
        this.restTemplate=template;
        this.headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("appKey",thingworxInfo.getAdminKey());

    }
    public ResponseEntity<String> requestSecurityCode(String mail) throws Exception {

        ServiceInfo serviceInfo = parameters.TWApis.get("requestSecurityCode");
        if(thingworxInfo.getUrl() == null){
            throw ConsumerException.urlEmpty().getEx();
        }

        if(!ResponseParser.mailChecker(mail)){
            return  ExceptionHandler.handleException(ParserException.invalidMail());
        }
        String body ="{\"userEmail\":\""+mail+"\"}";
        String apiUrl = thingworxInfo.getUrl()+serviceInfo.getUrl();
        HttpEntity<String> entity = new HttpEntity<String>(body,headers);
        ResponseEntity<String> response = restTemplate
                .exchange(apiUrl,
                        HttpMethod.POST, entity, String.class);
        if(response.getStatusCode().equals(HttpStatus.FOUND)){

            JSONResponseBuilder responseBuilder = new JSONResponseBuilder();
            responseBuilder.setStatus("Service has failed.Please check your Credentials and Parameters.");
            responseBuilder.setResponse(false);
            responseBuilder.setData(new JSONArray());

            return   ResponseEntity
                    .status(HttpStatus.OK).headers(headers)
                    .body(responseBuilder.getJSONResponse());
        }
        String result = response.getBody();
        result = ResponseParser.parseSecurityCode(result);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }
    public ResponseEntity<String> changePasswordWithCode(String body) throws Exception {

        ServiceInfo serviceInfo = Parameters.TWApis.get("changePasswordWithCode");

        if(thingworxInfo.getUrl() == null){
            throw ConsumerException.urlEmpty().getEx();
        }

        String apiUrl = thingworxInfo.getUrl()+serviceInfo.getUrl();
        HttpEntity<String> entity = new HttpEntity<String>(body,headers);
        ResponseEntity<String> response = restTemplate
                .exchange(apiUrl,
                        HttpMethod.POST, entity, String.class);
        String result = response.getBody();
        result = ResponseParser.parseChangePassword(result);

        ResponseEntity<String> stringResponseEntity = new ResponseEntity<>(result, HttpStatus.OK);
        return  stringResponseEntity;
    }
    public ResponseEntity<String> validateSecurityCode(String body) throws Exception {

        ServiceInfo serviceInfo = parameters.TWApis.get("validateSecurityCode");

        if(thingworxInfo.getUrl() == null){
            throw ConsumerException.urlEmpty().getEx();
        }

        String apiUrl = thingworxInfo.getUrl()+serviceInfo.getUrl();
        HttpEntity<String> entity = new HttpEntity<String>(body,headers);
        String result ="";
        try {
            ResponseEntity<String> response = restTemplate
                    .exchange(apiUrl,
                            HttpMethod.POST, entity, String.class);
            result = response.getBody();
        }
        catch (Exception ex){
            JSONResponseBuilder responseBuilder = JSONResponseBuilder.builder().response(false).data(new JSONArray().put(ex.getMessage())).build();
            return   ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .headers(headers)
                    .body(responseBuilder.getJSONResponse());
        }
        result = ResponseParser.parseValidateCode(result);

        ResponseEntity<String> stringResponseEntity = new ResponseEntity<>(result, HttpStatus.OK);
        return  stringResponseEntity;
    }

}
