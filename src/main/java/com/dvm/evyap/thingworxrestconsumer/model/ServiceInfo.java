package com.dvm.evyap.thingworxrestconsumer.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ServiceInfo {

    private EntityType entityType;
    private ServiceType serviceType;
    private String entityName;
    private String serviceName;
    private Long period;
    private Long timeout;
    private Long response;
    private String url;

    public String getUrl() {
        if(url !=null)
           return url;
        else
            if(EntityType.RUNTIME.equals(entityType))
                return "/"+entityType.getType();
             else
                return  "/"+entityType.getType()+"/"+entityName+"/"+serviceType.getService()+"/"+serviceName;
    }
}
