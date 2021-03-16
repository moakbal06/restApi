package com.dvm.evyap.thingworxrestconsumer.service;

import com.dvm.evyap.thingworxrestconsumer.model.ServiceInfo;
import com.dvm.evyap.thingworxrestconsumer.model.ThingworxInfo;
import com.dvm.evyap.thingworxrestconsumer.dvmexception.ConsumerException;
import com.dvm.evyap.thingworxrestconsumer.utils.Parameters;
import com.dvm.evyap.thingworxrestconsumer.utils.ResponseParser;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.request.RequestContextHolder;

import java.util.Arrays;
import java.util.Map;

@Service
public class LoginService {

    RestTemplate restTemplate;

    ThingworxInfo thingworxInfo;

    HttpHeaders headers;

    Parameters parameters;


    public LoginService(ThingworxInfo thingworxInfo, RestTemplate template, Parameters parameters) {
        this.parameters=parameters;
        this.thingworxInfo=thingworxInfo;
        this.restTemplate=template;
        this.headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(new MediaType[] { MediaType.APPLICATION_JSON }));
        headers.setContentType(MediaType.APPLICATION_JSON);
    }

    public ResponseEntity<String> login(Map<String, String> userHeaders) throws Exception {
        Object generatedKey = RequestContextHolder.currentRequestAttributes().getAttribute("generatedKey", 1);

        if(userHeaders.get("appkey") == null) {
            headers.remove("Authorization");
            headers.remove("appkey");
            ResponseEntity<String> appKeyResponse = getAppKey(userHeaders);

            JSONObject jsonObject = new JSONObject(appKeyResponse.getBody());
            JSONObject data = jsonObject.getJSONObject("data");
            JSONArray myArrList = data.getJSONArray("myArrayList");


            if(myArrList.length()>0) {
                JSONObject obj = (JSONObject) myArrList.get(0);
                JSONObject map = obj.getJSONObject("map");
                String appKey = map.get("appKey").toString();
                headers.set("appKey", appKey);
                ResponseEntity<String> responseHeader = new ResponseEntity<String>("{\"status\":\"Login Successful.Use the appKey in header for the other services\",\"data\":[],\"response\":true,\"isKVKKConfirmed\":true}", headers, HttpStatus.OK);

                return responseHeader;
            }else{

                ResponseEntity<String> stringResponseEntity = generateAppKey(userHeaders);
                if(stringResponseEntity.getHeaders().get("twx-mobile-token") != null && generatedKey == null )  {
                    RequestContextHolder.currentRequestAttributes().setAttribute("generatedKey",true,1);
                    return login(userHeaders);
                }else {
                    return stringResponseEntity;
                }
            }
        }else{
            ResponseEntity<String> responseHeader = new ResponseEntity<String>("{\"status\":\"Login Successful.Use the appKey in header for the other services\",\"data\":[],\"response\":true}", headers, HttpStatus.OK);
            return responseHeader;
        }
    }

    public ResponseEntity<String> generateAppKey(Map<String, String> userHeaders) throws Exception {


        ServiceInfo serviceInfo = parameters.TWApis.get("generateAppKey");
        if(thingworxInfo.getUrl() == null){
            throw ConsumerException.urlEmpty().getEx();
        }
        String authorization = userHeaders.get("authorization");
        String encodedUsernamePassword = ResponseParser.parseAuthenticationToUserNameAndEncoded(authorization).get("encodedUsernamePassword");
        headers.set("Authorization","Mobile "+encodedUsernamePassword);
        String apiUrl = thingworxInfo.getUrl()+serviceInfo.getUrl();
        HttpEntity<String> entity = new HttpEntity<String>(null,headers);
        ResponseEntity<String> response = restTemplate
                .exchange(apiUrl,
                        HttpMethod.GET, entity, String.class);
        String result = response.getBody();
        ResponseEntity<String> stringResponseEntity = new ResponseEntity<>(result,response.getHeaders(), HttpStatus.OK);
        return  stringResponseEntity;
    }

    public ResponseEntity<String> getAppKey(Map<String, String> userHeaders) throws Exception {

        String authorizationHeader = userHeaders.get("authorization");
        headers.add("Authorization",authorizationHeader);

        ServiceInfo serviceInfo = parameters.TWApis.get("getAppKey");
        String userName = ResponseParser.parseAuthenticationToUserNameAndEncoded(authorizationHeader).get("username");
        serviceInfo.setEntityName(userName);
        if(thingworxInfo.getUrl() == null){
            throw ConsumerException.urlEmpty().getEx();
        }
        String body ="{\"expiredKeys\":false,\"unexpiredKeys\":true}";

        String apiUrl = thingworxInfo.getUrl()+serviceInfo.getUrl();
        HttpEntity<String> entity = new HttpEntity<String>(body,headers);
        ResponseEntity<String> response = restTemplate
                .exchange(apiUrl,
                        HttpMethod.POST, entity, String.class);
        String result = response.getBody();
        result = ResponseParser.parseBodyAppKey(result);

        ResponseEntity<String> stringResponseEntity = new ResponseEntity<>(result, HttpStatus.OK);
        return  stringResponseEntity;
    }
    public String greet() {
        return "Hello, World";
    }
}
