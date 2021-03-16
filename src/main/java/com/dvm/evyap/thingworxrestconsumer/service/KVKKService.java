package com.dvm.evyap.thingworxrestconsumer.service;

import com.dvm.evyap.thingworxrestconsumer.dvmexception.ConsumerException;
import com.dvm.evyap.thingworxrestconsumer.model.ThingworxInfo;
import com.dvm.evyap.thingworxrestconsumer.utils.JSONResponseBuilder;
import com.dvm.evyap.thingworxrestconsumer.model.ServiceInfo;
import com.dvm.evyap.thingworxrestconsumer.utils.Parameters;
import com.dvm.evyap.thingworxrestconsumer.utils.ResponseParser;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.Map;

@Service
public class KVKKService {

    RestTemplate restTemplate;

    ThingworxInfo thingworxInfo;

    HttpHeaders headers;

    Parameters parameters;

    String kvkkVersion;


    public KVKKService(ThingworxInfo thingworxInfo, RestTemplate template, Parameters parameters) {
        this.parameters=parameters;
        this.thingworxInfo=thingworxInfo;
        this.restTemplate=template;
        this.headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("appKey",thingworxInfo.getAdminKey());
        kvkkVersion = thingworxInfo.getKvkkVersion();

    }
    public ResponseEntity<String> checkKVKK(Map<String, String> userHeaders) throws Exception {

        ServiceInfo serviceInfo = parameters.TWApis.get("checkKVKK");
        if(thingworxInfo.getUrl() == null){
            throw ConsumerException.urlEmpty().getEx();
        }

        String authorizationHeader = userHeaders.get("authorization");
        String userName = ResponseParser.parseAuthenticationToUserNameAndEncoded(authorizationHeader).get("username");

        String body ="{\"UserName\":\""+userName+"\",\"version\":\""+kvkkVersion+"\"}";
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
        result = ResponseParser.parseKVKK(result);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }
    public ResponseEntity<String> addKvkkByUserName(String body) throws Exception {

        ServiceInfo serviceInfo = Parameters.TWApis.get("addKVKK");

        if (thingworxInfo.getUrl() == null) {
            throw ConsumerException.urlEmpty().getEx();
        }
        JsonParser parser = new JsonParser();
        JsonObject jsonBody = parser.parse(body).getAsJsonObject();
        jsonBody.addProperty("KVKK_Version",kvkkVersion);

        String apiUrl = thingworxInfo.getUrl() + serviceInfo.getUrl();
        HttpEntity<String> entity = new HttpEntity<String>(jsonBody.toString(), headers);
        ResponseEntity<String> response = restTemplate
                .exchange(apiUrl,
                        HttpMethod.POST, entity, String.class);
        String result = response.getBody();
        result = ResponseParser.parseKVKK(result);

        ResponseEntity<String> stringResponseEntity = new ResponseEntity<>(result, HttpStatus.OK);
        return stringResponseEntity;
    }
    public ResponseEntity<String> addKvkkByMail(String body) throws Exception {

        ServiceInfo serviceInfo = Parameters.TWApis.get("addKVKK");

        if (thingworxInfo.getUrl() == null) {
            throw ConsumerException.urlEmpty().getEx();
        }
        JsonParser parser = new JsonParser();
        JsonObject jsonBody = parser.parse(body).getAsJsonObject();
        jsonBody.addProperty("KVKK_Version",kvkkVersion);

        String apiUrl = thingworxInfo.getUrl() + serviceInfo.getUrl();
        HttpEntity<String> entity = new HttpEntity<String>(jsonBody.toString(), headers);
        ResponseEntity<String> response = restTemplate
                .exchange(apiUrl,
                        HttpMethod.POST, entity, String.class);
        String result = response.getBody();
        result = ResponseParser.parseKVKK(result);

        ResponseEntity<String> stringResponseEntity = new ResponseEntity<>(result, HttpStatus.OK);
        return stringResponseEntity;
    }
    public ResponseEntity<String> getKvkkText() throws Exception {

        ServiceInfo serviceInfo = Parameters.TWApis.get("getKvkkText");

        if (thingworxInfo.getUrl() == null) {
            throw ConsumerException.urlEmpty().getEx();
        }

        String body = "{\"version\":\""+kvkkVersion+"\"}";
        String apiUrl = thingworxInfo.getUrl() + serviceInfo.getUrl();
        HttpEntity<String> entity = new HttpEntity<String>(body, headers);
        ResponseEntity<String> response = restTemplate
                .exchange(apiUrl,
                        HttpMethod.POST, entity, String.class);
        String result = response.getBody();
        ResponseEntity<String> stringResponseEntity = new ResponseEntity<>(result, HttpStatus.OK);
        return stringResponseEntity;
    }

    public ResponseEntity<String> checkKVKKMail(String mail) throws Exception {
        ServiceInfo serviceInfo = parameters.TWApis.get("checkKVKK");
        if(thingworxInfo.getUrl() == null){
            throw ConsumerException.urlEmpty().getEx();
        }

        String body ="{\"UserMail\":\""+mail+"\",\"version\":\""+kvkkVersion+"\"}";
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
        result = ResponseParser.parseKVKK(result);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
