package com.dvm.evyap.thingworxrestconsumer.utils;
import com.dvm.evyap.thingworxrestconsumer.dvmexception.ConsumerException;
import com.dvm.evyap.thingworxrestconsumer.dvmexception.ParserException;
import org.hibernate.validator.internal.constraintvalidators.hv.EmailValidator;


import com.dvm.evyap.thingworxrestconsumer.model.BandInfo;
import com.google.gson.*;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.*;
import java.util.stream.StreamSupport;

public  class ResponseParser {

    public static String parseBodyListLevel1(String result) {

        JSONObject obj = new JSONObject(result);
        JSONArray rows= obj.getJSONArray("rows");
        JSONArray newArray= new JSONArray();

        StreamSupport.stream(rows.spliterator(), false)
                .forEach(row -> setFactoryCodeandName((JSONObject)row,newArray));

        JSONResponseBuilder responseBuilder = JSONResponseBuilder.builder().response(true).data(newArray).status("Respose with data").build();
        return responseBuilder.getJSONResponse();

    }
    private static void setFactoryCodeandName(JSONObject row,JSONArray array) {
        array.put(new HashMap<String, String>() {{
            put("FactoryCode", row.get("FactoryCode").toString());
            put("FactoryName", row.get("FactoryName").toString());
        }});
    }

    public static String parseBodyListLevel2(String result) {
        JSONObject obj = new JSONObject(result);
        JSONArray rows= obj.getJSONArray("rows");
        JSONArray newArray= new JSONArray();

        StreamSupport.stream(rows.spliterator(), false)
                .forEach(row -> setLineName((JSONObject)row,newArray));

        JSONResponseBuilder responseBuilder = JSONResponseBuilder.builder().response(true).data(newArray).status("Respose with data").build();

        return responseBuilder.getJSONResponse();
    }
    private static void setLineName(JSONObject row,JSONArray array) {
        array.put(new HashMap<String, String>() {{
            put("LineName", row.get("LineName").toString());
            put("FactoryName", row.get("FactoryName").toString());
        }});
    }
    public static String parseBodyBandInfo(String result) throws Exception {
        JSONObject obj = new JSONObject(result);
        JSONArray rows= obj.getJSONArray("rows");
        final Gson gson = new GsonBuilder()
                .registerTypeAdapter(Date.class, (JsonDeserializer<Date>) (jsonElement, type, context) -> new Date(jsonElement.getAsJsonPrimitive().getAsLong()))
                .create();
        if(rows.length() <= 0){
            throw  ConsumerException.dataEmpty();
        }
        BandInfo bandInfo = gson.fromJson(String.valueOf(rows.get(0)), BandInfo.class);
        String jsonString = gson.toJson(bandInfo);
        JSONResponseBuilder responseBuilder = new JSONResponseBuilder();
        responseBuilder.setStatus("Respose with data");
        responseBuilder.setResponse(true);
        JSONObject obje = new JSONObject(jsonString);
        JSONArray arr =new JSONArray();
        arr.put(obje);
        responseBuilder.setData(arr);
        return responseBuilder.getJSONResponse();
    }

    public static String parseAllBodyBandInfo(String result) throws ConsumerException {
        JSONObject obj = new JSONObject(result);
        JSONArray rows= obj.getJSONArray("rows");
        final Gson gson = new GsonBuilder()
                .registerTypeAdapter(Date.class, (JsonDeserializer<Date>) (jsonElement, type, context) -> new Date(jsonElement.getAsJsonPrimitive().getAsLong()))
                .create();
        if(rows.length() <= 0){
            throw  ConsumerException.dataEmpty();
        }
        BandInfo[] bandInfo = gson.fromJson(String.valueOf(rows), BandInfo[].class);
        String jsonString = gson.toJson(bandInfo);
        JSONResponseBuilder responseBuilder = JSONResponseBuilder.builder().response(true).data(new JSONArray(jsonString)).status("Respose with data").build();

        return responseBuilder.getJSONResponse();
    }
    public static Map<String,String> parseAuthenticationToUserNameAndEncoded(String authHeader) throws Exception {

        try {
            if (authHeader != null && authHeader.contains("Basic")) {
                String encodedUsernamePassword = authHeader.substring("Basic ".length()).trim();
                byte[] decode = Base64.getDecoder().decode(encodedUsernamePassword);
                String usernamePassword = new String(decode);
                int seperatorIndex = usernamePassword.indexOf(':');
                String username = usernamePassword.substring(0, seperatorIndex);
                Map<String, String> theThings = new HashMap<>();
                theThings.put("username",username);
                theThings.put("encodedUsernamePassword",encodedUsernamePassword);
                return theThings;
            } else {
                throw  ParserException.notBasic();
            }}
        catch (Exception e){
            if (e instanceof ParserException){
                throw e;
            }else {
                e.printStackTrace();
                throw ParserException.authorizationHeaderFail();
            }
        }
    }
    public static String parseBodyAppKey(String result) {

        JSONObject obj = new JSONObject(result);
        JSONArray rows= obj.getJSONArray("rows");
        JSONArray newArray= new JSONArray();

        StreamSupport.stream(rows.spliterator(), false)
                .forEach(row -> setAppKey((JSONObject)row,newArray));

        JSONResponseBuilder responseBuilder = new JSONResponseBuilder();
        responseBuilder.setStatus("Respose with data");
        responseBuilder.setResponse(true);
        List<String> list = new ArrayList<String>();
        for (int i=0; i<newArray.length(); i++) {
            list.add( newArray.get(i).toString() );
        }
        String[] stringArray = list.toArray(new String[list.size()]);

        responseBuilder.setData(newArray);

        return responseBuilder.getJSONResponse();

    }
    private static void setAppKey(JSONObject row,JSONArray array) {
        array.put(new HashMap<String, String>() {{
            put("appKey", row.get("key").toString());
        }});
    }

    public static boolean mailChecker(String mail) {

        EmailValidator emailValidator =new EmailValidator();
       return emailValidator.isValid(mail,null);

    }

    public static String parseSecurityCode(String result) {
        JSONObject obj = new JSONObject(result);
        JSONResponseBuilder responseBuilder = new JSONResponseBuilder();

        if(obj.getBoolean("status")) {
            responseBuilder.setStatus("Güvenlik kodu gönderildi. Kodu kullanmadığınız takdirde 5 dakika içinde silinecektir.");
            responseBuilder.setResponse(true);
            responseBuilder.setData(new JSONArray("[{securityCode:" + obj.getString("securityCode").substring(0,1)+"*****}]"));

        } else {
            responseBuilder.setStatus("Güvenlik kodu gönderilemedi");
            responseBuilder.setResponse(false);
            responseBuilder.setData(new JSONArray());

        }
        return  responseBuilder.getJSONResponse();
    }

    public static String parseChangePassword(String result) {
        JSONObject obj = new JSONObject(result);
        JSONResponseBuilder responseBuilder = new JSONResponseBuilder();

        if(obj.getBoolean("status")) {
            responseBuilder.setStatus("Şifreniz başarıyla değiştirilmiştir.");
            responseBuilder.setResponse(true);
            responseBuilder.setData(new JSONArray("[{\"user\":" + obj.getString("username")+"}]"));
        }else{
            responseBuilder.setStatus(obj.getString("statusMessage"));
            responseBuilder.setResponse(false);
            responseBuilder.setData(new JSONArray("[{\"user\":" + obj.getString("username")+"}]"));
        }
        return  responseBuilder.getJSONResponse();
    }

    public static String parseValidateCode(String result) {
        JSONObject obj = new JSONObject(result);
        JSONResponseBuilder responseBuilder = new JSONResponseBuilder();
        if(obj.getJSONArray("rows") != null && obj.getJSONArray("rows").getJSONObject(0).getBoolean("result")){
            responseBuilder.setStatus("Güvenlik kodu eşleşti");
            responseBuilder.setResponse(true);
            responseBuilder.setData(new JSONArray());
        }else{
            responseBuilder.setStatus("Güvenlik kodu eşleşmedi.");
            responseBuilder.setResponse(false);
            responseBuilder.setData(new JSONArray("[{\"user\":" + obj.getString("username")+"}]"));
        }
        return  responseBuilder.getJSONResponse();
    }

    public static String parseKVKK(String result) {
        JSONObject obj = new JSONObject(result);
        JSONResponseBuilder responseBuilder = new JSONResponseBuilder();

        if (obj.getBoolean("status")) {
            responseBuilder.setStatus("Kullanıcı KVKK Sözleşmesini kabul etmiştir.");
            responseBuilder.setResponse(true);
            responseBuilder.setData(new JSONArray("[{\"user\":" + obj.getString("username") + "}]"));
        } else {
            responseBuilder.setStatus(obj.getString("statusMessage"));
            responseBuilder.setResponse(false);
            responseBuilder.setIsKVKKConfirmed(false);
            if(obj.has("username"))
            responseBuilder.setData(new JSONArray("[{\"user\":" + obj.getString("username") + "}]"));
            else{
            responseBuilder.setData(new JSONArray());
            }
            responseBuilder.setDataString("[]");
        }
        return responseBuilder.getJSONResponse();
    }
    public static String parseGenericResponse(String result) throws ConsumerException {
        JSONObject obj = new JSONObject(result);
        JSONArray array = obj.getJSONArray("array");
        JSONObject o = array.optJSONObject(0);
        if(obj.length()<1 || (o != null && o.has("count") && o.getInt("count") == 0) || (o == null && array.length()<1)){
            throw  ConsumerException.dataEmpty();
        }else if (o != null){
            o.remove("count");
        }
        JSONResponseBuilder responseBuilder = new JSONResponseBuilder();
        responseBuilder.setStatus("Respose with data");
        responseBuilder.setResponse(true);
        if(obj.has("array"))
            responseBuilder.setDataString((obj.get("array").toString()));
        else
            responseBuilder.setDataString(obj.toString());

        return responseBuilder.getJSONResponsesString();
    }

}
