package com.dvm.evyap.thingworxrestconsumer.controller;

import com.dvm.evyap.thingworxrestconsumer.model.StringResponse;
import com.dvm.evyap.thingworxrestconsumer.service.KVKKService;
import com.dvm.evyap.thingworxrestconsumer.utils.ExceptionHandler;
import io.swagger.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class KvkkRestController {

    private static final Logger logger = LoggerFactory.getLogger(KvkkRestController.class);

    @Autowired
    KVKKService kvkkService;

    @RequestMapping(value = "/kvkk/getKvkkText", method = RequestMethod.GET)
    @ApiOperation(value = "Get Kvkk Text as Html",response = StringResponse.class)
    public ResponseEntity<String> getKvkkText() {
        logger.info("getKvkkText");
        ResponseEntity<String> stringResponseEntity = null;
        try {
            ResponseEntity<String> stringResponseEntityKvkk = kvkkService.getKvkkText();
             return stringResponseEntityKvkk;

        } catch (Exception e) {
            return ExceptionHandler.handleException(e);
        }
    }

    @RequestMapping(value = "/kvkk/confirmKvkkByUserName", method = RequestMethod.POST)
    @ApiOperation(value = "Confirm KVKK By User Name",response = StringResponse.class)
    @ApiImplicitParams(
            value = @ApiImplicitParam(
                    name = "body",
                    paramType = "body",
                    value = "{\"UserName\":\"test.user\"}",
                    examples = @Example(
                            value =   @ExampleProperty(
                                    mediaType = "application/json",
                                    value = "{\"UserName\":\"test.user\"}"
                            )
                    )
            )
    )
    public ResponseEntity<String> confirmKvkkByUserName(@RequestBody String body) {
        logger.info("confirmKVKK");
        ResponseEntity<String> stringResponseEntity = null;
        try {
            ResponseEntity<String> stringResponseEntityKvkk = kvkkService.addKvkkByUserName(body);
            return stringResponseEntityKvkk;

        } catch (Exception e) {
            return ExceptionHandler.handleException(e);
        }
    }
    @RequestMapping(value = "/kvkk/confirmKvkkByMail", method = RequestMethod.POST)
    @ApiOperation(value = "Confirm KVKK By Mail",response = StringResponse.class)
    @ApiImplicitParams(
            value = @ApiImplicitParam(
                    name = "body",
                    paramType = "body",
                    value = "{\"UserMail\":\"test.user@test.com\"}",
                    examples = @Example(
                            value =   @ExampleProperty(
                                    mediaType = "application/json",
                                    value = "{\"UserName\":\"test.user@test.com\"}"
                            )
                    )
            )
    )
    public ResponseEntity<String> confirmKvkkByMail(@RequestBody String body) {
        logger.info("confirmKVKK");
        ResponseEntity<String> stringResponseEntity = null;
        try {
            ResponseEntity<String> stringResponseEntityKvkk = kvkkService.addKvkkByMail(body);
            return stringResponseEntityKvkk;

        } catch (Exception e) {
            return ExceptionHandler.handleException(e);
        }
    }
}
