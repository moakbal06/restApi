package com.dvm.evyap.thingworxrestconsumer.controller;


import com.dvm.evyap.thingworxrestconsumer.model.StringResponse;
import com.dvm.evyap.thingworxrestconsumer.service.KVKKService;
import com.dvm.evyap.thingworxrestconsumer.service.LoginService;
import com.dvm.evyap.thingworxrestconsumer.utils.ExceptionHandler;
import com.dvm.evyap.thingworxrestconsumer.utils.JSONResponseBuilder;
import com.google.gson.Gson;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;

import java.util.Map;

@RestController
public class LoginRestController {
    private static final Logger logger = LoggerFactory.getLogger(LoginRestController.class);

    @Autowired
    LoginService loginService;

    @Autowired
    KVKKService kvkkService;

    Gson gson = new Gson();

    @RequestMapping(value = "/Login", method = RequestMethod.GET)
    @ApiOperation(value = "Login with Basic Authentication",response = StringResponse.class)
    @ApiImplicitParam(name = "Authorization", value = "Basic [base64 encoded username:password]", required = true, allowEmptyValue = false, paramType = "Header", dataTypeClass = String.class, example = "Basic b251ci5ha2JhbDoxMjM0NTY3ODkw")
    public ResponseEntity<String> Login(@RequestHeader Map<String, String> Authorization) {
        logger.info("Login");
        try {
            RequestContextHolder.currentRequestAttributes().setAttribute("generatedKey",null,1);
            ResponseEntity<String> stringResponseEntityKvkk = kvkkService.checkKVKK(Authorization);
            JSONResponseBuilder resObj = gson.fromJson(stringResponseEntityKvkk.getBody(), JSONResponseBuilder.class);
            if(resObj.getResponse()) {
                return loginService.login(Authorization);
            }
           else{
                return  new ResponseEntity<>(resObj.getJSONResponsesStringKvkk(), HttpStatus.OK);
            }
        } catch (Exception e) {
            return ExceptionHandler.handleException(e);
            }
        }
}
