package com.dvm.evyap.thingworxrestconsumer.service;


import com.dvm.evyap.thingworxrestconsumer.dvmexception.ConsumerException;
import com.dvm.evyap.thingworxrestconsumer.model.ThingworxInfo;
import com.dvm.evyap.thingworxrestconsumer.utils.JSONResponseBuilder;
import com.dvm.evyap.thingworxrestconsumer.utils.ResponseParser;
import com.dvm.evyap.thingworxrestconsumer.model.ServiceInfo;
import com.dvm.evyap.thingworxrestconsumer.utils.Parameters;
import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.Map;


@Service
public class ConsumeWebService {

    RestTemplate restTemplate;

    ThingworxInfo thingworxInfo;

    HttpHeaders headers;


    @Autowired
    public ConsumeWebService(ThingworxInfo thingworxInfo,RestTemplate template) {
        this.thingworxInfo=thingworxInfo;
        this.restTemplate=template;
        this.headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        headers.setContentType(MediaType.APPLICATION_JSON);
    }

    public ResponseEntity<String> getShiftReport(String body) throws Exception {
        ServiceInfo serviceInfo = Parameters.TWApis.get("getShiftReport");
        if(thingworxInfo.getUrl() == null){
            throw ConsumerException.urlEmpty().getEx();
        }
        headers.set("appKey","ae745ecb-b064-48c5-97a8-2618171b0648");
        String apiUrl = thingworxInfo.getUrl()+serviceInfo.getUrl();
        HttpEntity<String> entity = new HttpEntity<>(body, headers);
        ResponseEntity<String> response = restTemplate
                .exchange(apiUrl,
                        HttpMethod.POST, entity, String.class);
        String result = response.getBody();
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
    public ResponseEntity<String> getFactories(String body, Map<String, String> appKeyHeader) throws Exception {
        String appKey;
        headers.remove("appKey");
        ServiceInfo serviceInfo = Parameters.TWApis.get("getFactories");
        if(thingworxInfo.getUrl() == null){
           throw  ConsumerException.urlEmpty();
        }
        if(appKeyHeader.get("appkey")==null && appKeyHeader.get("appKey")==null){
            throw ConsumerException.appKeyEmtpy();
        }else{
            appKey = appKeyHeader.get("appkey") ==null ? appKeyHeader.get("appKey") : appKeyHeader.get("appkey");
        }
        headers.add("appKey",appKey);
        String apiUrl = thingworxInfo.getUrl()+serviceInfo.getUrl();
        HttpEntity<String> entity = new HttpEntity<>(body, headers);
        ResponseEntity<String> response = restTemplate
                .exchange(apiUrl,
                        HttpMethod.POST, entity, String.class);
        if(response.getStatusCode().equals(HttpStatus.FOUND)){
            JSONResponseBuilder responseBuilder = new JSONResponseBuilder();
            responseBuilder.setStatus("Service has failed.Please check your Credentials and Parameters.");
            responseBuilder.setResponse(false);
            responseBuilder.setData(new JSONArray());
            return   ResponseEntity
                    .status(response.getStatusCode()).headers(headers)
                    .body(responseBuilder.getJSONResponse());
        }
        String result = response.getBody();
        result = ResponseParser.parseBodyListLevel1(result);
        return new ResponseEntity<>(result,headers, HttpStatus.OK);
    }
    public ResponseEntity<String> getLines(String body, Map<String, String> appKeyHeader) throws Exception {
        String appKey;
        headers.remove("appKey");
        ServiceInfo serviceInfo = Parameters.TWApis.get("getLines");
        if(thingworxInfo.getUrl() == null){
            throw  ConsumerException.urlEmpty();
        }
        if(appKeyHeader.get("appkey")==null && appKeyHeader.get("appKey")==null){
            throw ConsumerException.appKeyEmtpy();
        }else{
            appKey = appKeyHeader.get("appkey") ==null ? appKeyHeader.get("appKey") : appKeyHeader.get("appkey");
        }
        headers.add("appKey",appKey);
        String apiUrl = thingworxInfo.getUrl()+serviceInfo.getUrl();
        HttpEntity<String> entity = new HttpEntity<>(body, headers);
        ResponseEntity<String> response = restTemplate
                .exchange(apiUrl,
                        HttpMethod.POST, entity, String.class);
        if(response.getStatusCode().equals(HttpStatus.FOUND)){
            JSONResponseBuilder responseBuilder = new JSONResponseBuilder();
            responseBuilder.setStatus("Service has failed.Please check your Credentials and Parameters.");
            responseBuilder.setResponse(false);
            responseBuilder.setData(new JSONArray());
            return   ResponseEntity
                    .status(response.getStatusCode()).headers(headers)
                    .body(responseBuilder.getJSONResponse());
        }
        String result = response.getBody();
        result = ResponseParser.parseBodyListLevel2(result);
        return new ResponseEntity<>(result,headers, HttpStatus.OK);
    }

    public ResponseEntity<String> getLineInfo(String body, Map<String, String> appKeyHeader) throws Exception {
        String appKey;
        headers.remove("appKey");
        ServiceInfo serviceInfo = Parameters.TWApis.get("getLineInfo");
        if(thingworxInfo.getUrl() == null){
            throw  ConsumerException.urlEmpty();
        }
        if(appKeyHeader.get("appkey")==null && appKeyHeader.get("appKey")==null){
            throw ConsumerException.appKeyEmtpy();
        }else{
            appKey = appKeyHeader.get("appkey") ==null ? appKeyHeader.get("appKey") : appKeyHeader.get("appkey");
        }
        headers.add("appKey",appKey);
        String apiUrl = thingworxInfo.getUrl()+serviceInfo.getUrl();
        HttpEntity<String> entity = new HttpEntity<>(body, headers);
        ResponseEntity<String> response = restTemplate
               .exchange(apiUrl,
                       HttpMethod.POST, entity, String.class);
        String result = response.getBody();
        if(response.getStatusCode().equals(HttpStatus.FOUND)){

            JSONResponseBuilder responseBuilder = new JSONResponseBuilder();
            responseBuilder.setStatus("Service has failed.Please check your Credentials and Parameters.");
            responseBuilder.setResponse(false);
            responseBuilder.setData(new JSONArray());

            return   ResponseEntity
                    .status(response.getStatusCode()).headers(headers)
                    .body(responseBuilder.getJSONResponse());
        }
        result = ResponseParser.parseBodyBandInfo(result);
        return new ResponseEntity<>(result,headers, HttpStatus.OK);
    }
    public ResponseEntity<String> getAllLineInfo(String body, Map<String, String> appKeyHeader) throws Exception {
        String appKey;
        headers.remove("appKey");
        ServiceInfo serviceInfo = Parameters.TWApis.get("getAllLineInfo");
        if(thingworxInfo.getUrl() == null){
            throw  ConsumerException.urlEmpty();
        }
        if(appKeyHeader.get("appkey")==null && appKeyHeader.get("appKey")==null){
            throw ConsumerException.appKeyEmtpy();
        }else{
            appKey = appKeyHeader.get("appkey") ==null ? appKeyHeader.get("appKey") : appKeyHeader.get("appkey");
        }
        headers.add("appKey",appKey);
        String apiUrl = thingworxInfo.getUrl()+serviceInfo.getUrl();
        HttpEntity<String> entity = new HttpEntity<>(body, headers);
        ResponseEntity<String> response = restTemplate
                .exchange(apiUrl,
                        HttpMethod.POST, entity, String.class);
        if(response.getStatusCode().equals(HttpStatus.FOUND)){
            JSONResponseBuilder responseBuilder = new JSONResponseBuilder();
            responseBuilder.setStatus("Service has failed.Please check your Credentials and Parameters.");
            responseBuilder.setResponse(false);
            responseBuilder.setData(new JSONArray());

            return   ResponseEntity
                    .status(response.getStatusCode()).headers(headers)
                    .body(responseBuilder.getJSONResponse());
        }
        String result = response.getBody();
        result = ResponseParser.parseAllBodyBandInfo(result);
        return new ResponseEntity<>(result,headers, HttpStatus.OK);
    }
    public ResponseEntity<String> getAllLineInfoByLine(String body, Map<String, String> appKeyHeader) throws Exception {
        String appKey;
        headers.remove("appKey");
        ServiceInfo serviceInfo = Parameters.TWApis.get("getAllLineInfoByLine");
        if(thingworxInfo.getUrl() == null){
            throw  ConsumerException.urlEmpty();
        }
        if(appKeyHeader.get("appkey")==null && appKeyHeader.get("appKey")==null){
            throw ConsumerException.appKeyEmtpy();
        }else{
            appKey = appKeyHeader.get("appkey") ==null ? appKeyHeader.get("appKey") : appKeyHeader.get("appkey");
        }
        headers.add("appKey",appKey);
        String apiUrl = thingworxInfo.getUrl()+serviceInfo.getUrl();
        HttpEntity<String> entity = new HttpEntity<>(body, headers);
        ResponseEntity<String> response = restTemplate
                .exchange(apiUrl,
                        HttpMethod.POST, entity, String.class);
        if(response.getStatusCode().equals(HttpStatus.FOUND)){
            JSONResponseBuilder responseBuilder = new JSONResponseBuilder();
            responseBuilder.setStatus("Service has failed.Please check your Credentials and Parameters.");
            responseBuilder.setResponse(false);
            responseBuilder.setData(new JSONArray());

            return   ResponseEntity
                    .status(response.getStatusCode()).headers(headers)
                    .body(responseBuilder.getJSONResponse());
        }
        String result = response.getBody();
        result = ResponseParser.parseGenericResponse(result);
        return new ResponseEntity<>(result,headers, HttpStatus.OK);
    }

    public ResponseEntity<String> getAllLineInfoByKey(String body, Map<String, String> appKeyHeader) throws Exception {
        String appKey;
        headers.remove("appKey");
        ServiceInfo serviceInfo = Parameters.TWApis.get("getAllLineInfoByKey");
        if(thingworxInfo.getUrl() == null){
            throw  ConsumerException.urlEmpty();
        }
        if(appKeyHeader.get("appkey")==null && appKeyHeader.get("appKey")==null){
            throw ConsumerException.appKeyEmtpy();
        }else{
            appKey = appKeyHeader.get("appkey") ==null ? appKeyHeader.get("appKey") : appKeyHeader.get("appkey");
        }
        headers.add("appKey",appKey);
        String apiUrl = thingworxInfo.getUrl()+serviceInfo.getUrl();
        HttpEntity<String> entity = new HttpEntity<>(body, headers);
        ResponseEntity<String> response = restTemplate
                .exchange(apiUrl,
                        HttpMethod.POST, entity, String.class);
        if(response.getStatusCode().equals(HttpStatus.FOUND)){
            JSONResponseBuilder responseBuilder = new JSONResponseBuilder();
            responseBuilder.setStatus("Service has failed.Please check your Credentials and Parameters.");
            responseBuilder.setResponse(false);
            responseBuilder.setData(new JSONArray());

            return   ResponseEntity
                    .status(response.getStatusCode()).headers(headers)
                    .body(responseBuilder.getJSONResponse());
        }
        String result = response.getBody();
        result = ResponseParser.parseGenericResponse(result);
        return new ResponseEntity<>(result,headers, HttpStatus.OK);
    }
    public ResponseEntity<String> getKPIKeys(Map<String, String> appKeyHeader) throws Exception {
        String appKey;
        headers.remove("appKey");
        ServiceInfo serviceInfo = Parameters.TWApis.get("getKPIKeys");
        if(thingworxInfo.getUrl() == null){
            throw  ConsumerException.urlEmpty();
        }
        if(appKeyHeader.get("appkey")==null && appKeyHeader.get("appKey")==null){
            throw ConsumerException.appKeyEmtpy();
        }else{
            appKey = appKeyHeader.get("appkey") ==null ? appKeyHeader.get("appKey") : appKeyHeader.get("appkey");
        }
        headers.add("appKey",appKey);
        String apiUrl = thingworxInfo.getUrl()+serviceInfo.getUrl();
        HttpEntity<String> entity = new HttpEntity<>(headers);
        ResponseEntity<String> response = restTemplate
                .exchange(apiUrl,
                        HttpMethod.POST, entity, String.class);
        if(response.getStatusCode().equals(HttpStatus.FOUND)){
            JSONResponseBuilder responseBuilder = new JSONResponseBuilder();
            responseBuilder.setStatus("Service has failed.Please check your Credentials and Parameters.");
            responseBuilder.setResponse(false);
            responseBuilder.setData(new JSONArray());

            return   ResponseEntity
                    .status(response.getStatusCode()).headers(headers)
                    .body(responseBuilder.getJSONResponse());
        }
        String result = response.getBody();
        result = ResponseParser.parseGenericResponse(result);
        return new ResponseEntity<>(result,headers, HttpStatus.OK);
    }

}