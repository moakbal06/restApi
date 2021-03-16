package com.dvm.evyap.thingworxrestconsumer.controller;


import com.dvm.evyap.thingworxrestconsumer.model.StringResponse;
import com.dvm.evyap.thingworxrestconsumer.service.KVKKService;
import com.dvm.evyap.thingworxrestconsumer.utils.ExceptionHandler;
import com.dvm.evyap.thingworxrestconsumer.service.PasswordService;
import com.dvm.evyap.thingworxrestconsumer.utils.GenericGsonParser;
import com.dvm.evyap.thingworxrestconsumer.utils.JSONResponseBuilder;
import com.google.gson.Gson;
import io.swagger.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class PasswordRestController {


    @Autowired
    PasswordService passwordService;

    @Autowired
    KVKKService kvkkService;

    Gson gson = new Gson();


    private static final Logger logger = LoggerFactory.getLogger(PasswordRestController.class);


    @ApiOperation(value = "Reset Password with User Email", response = StringResponse.class)
    @RequestMapping(value="/password/resetPassword/{mail}", method = RequestMethod.GET)
    public ResponseEntity<String> resetPassword(@PathVariable String mail){
        logger.info("resetPassword");
        try {
        ResponseEntity<String> stringResponseEntityKvkk = kvkkService.checkKVKKMail(mail);
        JSONResponseBuilder resObj = GenericGsonParser.ParseEntity(stringResponseEntityKvkk.getBody(), JSONResponseBuilder.class);
        //JSONResponseBuilder resObj = gson.fromJson(stringResponseEntityKvkk.getBody(), JSONResponseBuilder.class);
            if(resObj.getResponse()) {
                return  passwordService.requestSecurityCode(mail);
            }
            else{
                return  new ResponseEntity<>(resObj.getJSONResponse(), HttpStatus.OK);
            }

        } catch (Exception e) {
            return  com.dvm.evyap.thingworxrestconsumer.utils.ExceptionHandler.handleException(e);
        }

    }
    @RequestMapping(value = "/password/changePasswordWithCode", method = RequestMethod.POST)
    @ApiOperation(value = "Change Password With Security Code",response = StringResponse.class)
    @ApiImplicitParams(
            value = @ApiImplicitParam(
                    name = "body",
                    paramType = "body",
                    value = "{\"securityCode\":\"HUD7DJ\",\"userEmail\":\"aaa@bbb.ccc\",\"newPassword\":\"****\",\"newPasswordConfirm\":\"****\"}",
                    examples = @Example(
                            value =   @ExampleProperty(
                                    mediaType = "application/json",
                                    value = "{\"SecurityCode\":\"HUD7DJ\",\"UserEmail\":\"aaa@bbb.ccc\",\"NewPassword\":\"****\",\"NewPasswordConfirm\":\"****\"}"
                            )
                    )
            )
    )
    public ResponseEntity<String> changePasswordWithCode(@RequestBody String body) {
        logger.info("changePasswordWithCode");
        try {
            return  passwordService.changePasswordWithCode(body);
        } catch (Exception e) {
          return  ExceptionHandler.handleException(e);

        }
    }
    @RequestMapping(value = "/password/validateSecurityCode", method = RequestMethod.POST)
    @ApiOperation(value = "Validate Security Code with Email",response = StringResponse.class)
    @ApiImplicitParams(
            value = @ApiImplicitParam(
                    name = "body",
                    paramType = "body",
                    value = "{\"securityCode\":\"HUD7DJ\",\"email\":\"aaa@bbb.ccc\"}",
                    examples = @Example(
                            value =   @ExampleProperty(
                                    mediaType = "application/json",
                                    value = "{\"securityCode\":\"HUD7DJ\",\"email\":\"aaa@bbb.ccc\"}"
                            )
                    )
            )
    )
    public ResponseEntity<String> validateSecurityCode(@RequestBody String body) {
        logger.info("validateSecurityCode");
        try {
            return  passwordService.validateSecurityCode(body);
        } catch (Exception e) {
            return  ExceptionHandler.handleException(e);

        }
    }
}
