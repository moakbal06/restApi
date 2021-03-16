package com.dvm.evyap.thingworxrestconsumer.utils;

import com.dvm.evyap.thingworxrestconsumer.model.EntityType;
import com.dvm.evyap.thingworxrestconsumer.model.ServiceType;
import com.dvm.evyap.thingworxrestconsumer.model.ServiceInfo;
import org.springframework.stereotype.Component;

import java.util.HashMap;

@Component
public class Parameters {

    public static HashMap<String, ServiceInfo> TWApis =new HashMap();

    public Parameters() {

        TWApis.put("getShiftReport",new ServiceInfo(
                EntityType.THING,
                ServiceType.SERVICE,
                "DMOM_JSONData_Thing",
                "GetShiftReport",
                null,
                null,
                null,
                null));

        TWApis.put("getFactories",new ServiceInfo(
                EntityType.THING,
                ServiceType.SERVICE,
                "DMOM_JSONData_Thing",
                "ListLevel1",
                null,
                null,
                null,
                null));
        TWApis.put("getLines",new ServiceInfo(
                EntityType.THING,
                ServiceType.SERVICE,
                "DMOM_JSONData_Thing",
                "ListLevel2",
                null,
                null,
                null,
                null));
        TWApis.put("getLineInfo",new ServiceInfo(
                EntityType.THING,
                ServiceType.SERVICE,
                "DMOM_JSONData_Thing",
                "GetLineInfo",
                null,
                null,
                null,
                null));
        TWApis.put("getAllLineInfo",new ServiceInfo(
                EntityType.THING,
                ServiceType.SERVICE,
                "DMOM_JSONData_Thing",
                "GetAllLineInfo",
                null,
                null,
                null,
                null));
        TWApis.put("getAllLineInfoByLine",new ServiceInfo(
                EntityType.THING,
                ServiceType.SERVICE,
                "DMOM_JSONData_Thing",
                "GetAllLineInfoByLine",
                null,
                null,
                null,
                null));
        TWApis.put("getAllLineInfoByLineCopy",new ServiceInfo(
                EntityType.THING,
                ServiceType.SERVICE,
                "DMOM_JSONData_Thing",
                "GetAllLineInfoByLineCopy",
                null,
                null,
                null,
                null));
        TWApis.put("getAllLineInfoByKey",new ServiceInfo(
                EntityType.THING,
                ServiceType.SERVICE,
                "DMOM_JSONData_Thing",
                "GetAllLineInfoByKey",
                null,
                null,
                null,
                null));
        TWApis.put("getKPIKeys",new ServiceInfo(
                EntityType.THING,
                ServiceType.SERVICE,
                "DMOM_JSONData_Thing",
                "GetKPIKeys",
                null,
                null,
                null,
                null));
        TWApis.put("generateAppKey",new ServiceInfo(
                EntityType.RUNTIME,
                null,
                null,
                null,
                null,
                null,
                null,
                null));
        TWApis.put("getAppKey",new ServiceInfo(
                EntityType.USERS,
                ServiceType.SERVICE,
                null,
                "GetApplicationKeyExpirationInfo",
                null,
                null,
                null,
                null));
        TWApis.put("requestSecurityCode",new ServiceInfo(
                EntityType.THING,
                ServiceType.SERVICE,
                "Evyap_MobileAppSecurity_API_Thing",
                "SendSecurityCodeViaEmail",
                null,
                null,
                null,
                null));
        TWApis.put("changePasswordWithCode",new ServiceInfo(
                EntityType.THING,
                ServiceType.SERVICE,
                "Evyap_MobileAppSecurity_API_Thing",
                "AssignNewPasswordWithSecurityCode",
                null,
                null,
                null,
                null));
        TWApis.put("validateSecurityCode",new ServiceInfo(
                EntityType.THING,
                ServiceType.SERVICE,
                "Evyap_MobileAppSecurity_API_Thing",
                "validateSecurityCode",
                null,
                null,
                null,
                null));
        TWApis.put("checkKVKK",new ServiceInfo(
                EntityType.THING,
                ServiceType.SERVICE,
                "Evyap_MobileAppSecurity_API_Thing",
                "KVKK_CheckUser",
                null,
                null,
                null,
                null));
        TWApis.put("addKVKK",new ServiceInfo(
                EntityType.THING,
                ServiceType.SERVICE,
                "Evyap_MobileAppSecurity_API_Thing",
                "KVKK_AddUser",
                null,
                null,
                null,
                null));
        TWApis.put("getKvkkText",new ServiceInfo(
                EntityType.THING,
                ServiceType.SERVICE,
                "Evyap_MobileAppSecurity_API_Thing",
                "getKVKKText",
                null,
                null,
                null,
                null));
    }

}
