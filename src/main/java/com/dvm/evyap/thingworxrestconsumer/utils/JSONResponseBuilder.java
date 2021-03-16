package com.dvm.evyap.thingworxrestconsumer.utils;

import com.google.gson.Gson;
import lombok.*;
import org.json.JSONArray;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
//@ToString(callSuper=true, includeFieldNames=true)
public class JSONResponseBuilder {


  @NonNull
  private String status;
  @NonNull
  private Boolean response ;

  private Boolean isKVKKConfirmed = true;

  private JSONArray data;
  private String dataString;

 public String getJSONResponse(){


    // JSONObject obj = new JSONObject(this.toString());
   //  String aa= obj.toString();

     Gson gson = new Gson();
     String s = gson.toJson(this,JSONResponseBuilder.class);
     return  s;
 }
    public String getJSONResponsesString(){

       return  "{\n" +
                "  \"data\": "+dataString+",\n" +
                "  \"response\": "+this.getResponse()+",\n" +
                "  \"status\": \""+this.getStatus()+"\"\n" +
                "}";
    }
    public String getJSONResponsesStringKvkk(){

        return  "{\n" +
                "  \"data\": "+dataString+",\n" +
                "  \"response\": "+this.getResponse()+",\n" +
                "  \"status\": \""+this.getStatus()+"\",\n" +
                "  \"isKVKKConfirmed\": "+this.getIsKVKKConfirmed()+"\n" +

                "}";
    }
}
