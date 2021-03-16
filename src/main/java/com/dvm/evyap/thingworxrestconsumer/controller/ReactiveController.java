package com.dvm.evyap.thingworxrestconsumer.controller;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.Example;
import io.swagger.annotations.ExampleProperty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.net.URISyntaxException;

@RestController
public class ReactiveController {

    private static final Logger logger = LoggerFactory.getLogger(PasswordRestController.class);

    @RequestMapping(value = "/GetShiftReportReactive", method = RequestMethod.POST)
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
    public Mono<String> GetShiftReportReactive(@RequestBody String body) throws URISyntaxException {

        logger.info("GetShiftReportReactive");

        WebClient webClient = WebClient.builder()
                .baseUrl("http://10.5.2.33:8080/Thingworx/Things/DMOM_JSONData_Thing/Services")
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .build();
        Mono<String> createdEmployee = webClient.post()
                .uri("/GetShiftReport")
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .header(HttpHeaders.ACCEPT,MediaType.APPLICATION_JSON_VALUE)
                .header("appKey", "ae745ecb-b064-48c5-97a8-2618171b0648")
                .body(Mono.just(body), String.class)
                .retrieve()
                .bodyToMono(String.class);
        return createdEmployee;
    }

}
