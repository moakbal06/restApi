package com.dvm.evyap.thingworxrestconsumer;

import com.dvm.evyap.thingworxrestconsumer.model.AllBandResponseInfo;
import com.dvm.evyap.thingworxrestconsumer.model.BandResponseInfo;
import com.dvm.evyap.thingworxrestconsumer.model.FactoryResponseInfo;
import com.dvm.evyap.thingworxrestconsumer.model.LineResponseInfo;
import com.dvm.evyap.thingworxrestconsumer.service.ConsumeWebService;
import com.dvm.evyap.thingworxrestconsumer.utils.ExceptionHandler;
import com.dvm.evyap.thingworxrestconsumer.model.*;
import io.swagger.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;

import java.util.Map;

@RestController
public class ThingworxRestApi {

    @Autowired
    ConsumeWebService webService;

    private static final Logger logger = LoggerFactory.getLogger(ThingworxRestApi.class);

    @RequestMapping(value = "/GetShiftReport", method = RequestMethod.POST)
    @ApiImplicitParams(
            @ApiImplicitParam(
                    name = "body",
                    value = "{\"sd\":\"11/01/2020 00:00\",  \"ed\":\"11/30/2020 00:00\"}",
                    examples = @Example(
                            @ExampleProperty(
                                    mediaType = "application/json",
                                    value = "{ 'body'： 'type': 'AAA'}"
                            )
                    )
            )
    )
    public ResponseEntity<String> getShiftReport(@RequestBody String body) {
        logger.info("GetShiftReport");
        ResponseEntity<String> stringResponseEntity = null;
        try {
            return  stringResponseEntity = webService.getShiftReport(body);
        } catch (Exception e) {
            if (e instanceof HttpClientErrorException){
                return ResponseEntity
                        .status(((HttpClientErrorException) e).getStatusCode())
                        .body(e.getMessage());
            }else {
                e.printStackTrace();
                return ResponseEntity
                        .status(HttpStatus.BAD_REQUEST)
                        .body(e.getMessage());
            }
        }
    }



    @RequestMapping(value = "/GetFactories", method = RequestMethod.POST)
    @ApiOperation(value = "Get Factories",response = FactoryResponseInfo.class)
    @ApiImplicitParams({
            @ApiImplicitParam(
                    name = "body",
                    examples = @Example(
                            @ExampleProperty(
                                    mediaType = "application/json",
                                    value = "{ 'body'： 'type': '{}'}"
                            )
                    )
            ),
            @ApiImplicitParam(name = "appKey", value = "Thingworx User App Key", required = true, allowEmptyValue = false, paramType = "Header", dataTypeClass = String.class, example = "19db41fe-86ed-4d0d-b7e1-0ce512e0e27d")
    }
    )
    public ResponseEntity<String> getFactories(@RequestBody String body,@RequestHeader Map<String, String> appKey) {
        logger.info("GetFactories");
        ResponseEntity<String> stringResponseEntity = null;
        try {
            Map<String, String> appkey=appKey;
            return  stringResponseEntity = webService.getFactories(body,appkey);
        } catch (Exception e) {
            return  com.dvm.evyap.thingworxrestconsumer.utils.ExceptionHandler.handleException(e);
        }
    }
    @RequestMapping(value = "/GetLines", method = RequestMethod.POST)
    @ApiOperation(value = "Get Lines",response = LineResponseInfo.class)
    @ApiImplicitParams(
            { @ApiImplicitParam(
                    name = "body",
                    paramType = "body",
                    value = "{\"FactoryName\":\"HIJYEN\"}",
                    examples = @Example(
                            value =   @ExampleProperty(
                                    mediaType = "application/json",
                                    value = "{ 'type': '{\"FactoryName\":\"HIJYEN\"}'}"
                            )
                    )
            ),
                    @ApiImplicitParam(name = "appKey", value = "Thingworx User App Key", required = true, allowEmptyValue = false, paramType = "Header", dataTypeClass = String.class, example = "19db41fe-86ed-4d0d-b7e1-0ce512e0e27d")
            }
    )
    public ResponseEntity<String> getLines(@RequestBody String body,@RequestHeader Map<String, String> appKey) {
        logger.info("GetListLevel2");
        ResponseEntity<String> stringResponseEntity = null;
        try {
            Map<String, String> appkey=appKey;
            return  stringResponseEntity = webService.getLines(body,appkey);
        } catch (Exception e) {
            return  com.dvm.evyap.thingworxrestconsumer.utils.ExceptionHandler.handleException(e);
        }
    }
    @RequestMapping(value = "/GetLineInfo", method = RequestMethod.POST)
    @ApiOperation(value = "Get Line Informations",response = BandResponseInfo.class)
    @ApiImplicitParams(
            {@ApiImplicitParam(
                    name = "body",
                    paramType = "body",
                    value = "{\"LineName\":\"D06\",\"FactoryName\":\"HIJYEN\"}",
                    examples = @Example(
                            value = @ExampleProperty(
                                    mediaType = "application/json",
                                    value = "{\"LineName\":\"D06\",\"FactoryName\":\"HIJYEN\"}"
                            )
                    )
            ),
                    @ApiImplicitParam(name = "appKey", value = "Thingworx User App Key", required = true, allowEmptyValue = false, paramType = "Header", dataTypeClass = String.class, example = "19db41fe-86ed-4d0d-b7e1-0ce512e0e27d")
            }
    )
    public ResponseEntity<String> getLineInfo(@RequestBody String body,@RequestHeader Map<String, String> appKey) {
        logger.info("getBandInfo");
        try {
            Map<String, String> appkey=appKey;
            return  webService.getLineInfo(body,appkey);
        } catch (Exception e) {
            return  com.dvm.evyap.thingworxrestconsumer.utils.ExceptionHandler.handleException(e);
        }
    }
    @RequestMapping(value = "/GetAllLineInfo", method = RequestMethod.POST)
    @ApiOperation(value = "Get All Line Informations",response = AllBandResponseInfo.class)
    @ApiImplicitParams(
            {@ApiImplicitParam(
                    name = "body",
                    paramType = "body",
                    value = "{\"FactoryName\":\"HIJYEN\"}",
                    examples = @Example(
                            value =   @ExampleProperty(
                                    mediaType = "application/json",
                                    value = "{\"FactoryName\":\"HIJYEN\"}"
                            )
                    )
            ),
            @ApiImplicitParam(name = "appKey", value = "Thingworx User App Key", required = true, allowEmptyValue = false, paramType = "Header", dataTypeClass = String.class, example = "19db41fe-86ed-4d0d-b7e1-0ce512e0e27d")
            }
    )
    public ResponseEntity<String> getAllLineInfo(@RequestBody String body,@RequestHeader Map<String, String> appKey) {
        logger.info("getAllLineInfo");
        try {
            Map<String, String> appkey=appKey;
            return  webService.getAllLineInfo(body,appkey);
        } catch (Exception e) {
           return com.dvm.evyap.thingworxrestconsumer.utils.ExceptionHandler.handleException(e);
        }
    }
    @RequestMapping(value = "/GetAllLineInfoByLine", method = RequestMethod.POST)
    @ApiOperation(value = "Get All Line Informations Line Group")
    @ApiImplicitParams(
            {@ApiImplicitParam(
                    name = "body",
                    paramType = "body",
                    value = "{\"FactoryName\":\"HIJYEN\"}",
                    examples = @Example(
                            value =   @ExampleProperty(
                                    mediaType = "application/json",
                                    value = "{\"FactoryName\":\"HIJYEN\"}"
                            )
                    )
            ),
                    @ApiImplicitParam(name = "appKey", value = "Thingworx User App Key", required = true, allowEmptyValue = false, paramType = "Header", dataTypeClass = String.class, example = "19db41fe-86ed-4d0d-b7e1-0ce512e0e27d")
            }
    )
    public ResponseEntity<String> getAllLineInfoByLine(@RequestBody String body,@RequestHeader Map<String, String> appKey) {
        logger.info("getAllLineInfoByLine");
        try {
            Map<String, String> appkey=appKey;
            return  webService.getAllLineInfoByLine(body,appkey);
        } catch (Exception e) {
            return com.dvm.evyap.thingworxrestconsumer.utils.ExceptionHandler.handleException(e);
        }
    }

    @RequestMapping(value = "/GetAllLineInfoByKey", method = RequestMethod.POST)
    @ApiOperation(value = "Get All Line Informations Key Group")
    @ApiImplicitParams(
            {@ApiImplicitParam(
                    name = "body",
                    paramType = "body",
                    value = "{\"FactoryName\":\"HIJYEN\"}",
                    examples = @Example(
                            value =   @ExampleProperty(
                                    mediaType = "application/json",
                                    value = "{\"FactoryName\":\"HIJYEN\"}"
                            )
                    )
            ),
                    @ApiImplicitParam(name = "appKey", value = "Thingworx User App Key", required = true, allowEmptyValue = false, paramType = "Header", dataTypeClass = String.class, example = "19db41fe-86ed-4d0d-b7e1-0ce512e0e27d")
            }
    )
    public ResponseEntity<String> GetAllLineInfoByKey(@RequestBody String body,@RequestHeader Map<String, String> appKey) {
        logger.info("getAllLineInfoByLine");
        try {
            Map<String, String> appkey=appKey;
            return  webService.getAllLineInfoByKey(body,appkey);
        } catch (Exception e) {
            return com.dvm.evyap.thingworxrestconsumer.utils.ExceptionHandler.handleException(e);
        }
    }

    @RequestMapping(value = "/GetKPIKeys", method = RequestMethod.POST)
    @ApiOperation(value = "Get All KPI Keys")
    @ApiImplicitParams(
            {
                    @ApiImplicitParam(name = "appKey", value = "Thingworx User App Key", required = true, allowEmptyValue = false, paramType = "Header", dataTypeClass = String.class, example = "19db41fe-86ed-4d0d-b7e1-0ce512e0e27d")
            }
    )
    public ResponseEntity<String> GetKPIKeys(@RequestHeader Map<String, String> appKey) {
        logger.info("GetKPIKeys");
        try {
            Map<String, String> appkey=appKey;
            return  webService.getKPIKeys(appkey);
        } catch (Exception e) {
            return ExceptionHandler.handleException(e);
        }
    }




}
