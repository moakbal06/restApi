package com.dvm.evyap.thingworxrestconsumer;

import com.dvm.evyap.thingworxrestconsumer.dvmexception.ParserException;
import com.dvm.evyap.thingworxrestconsumer.utils.JSONResponseBuilder;
import org.json.JSONArray;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2

public class ThingworxrestconsumerApplication extends SpringBootServletInitializer {

    private static final Logger logger = LoggerFactory.getLogger(ThingworxrestconsumerApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(ThingworxrestconsumerApplication.class, args);


        JSONResponseBuilder responseBuilder = JSONResponseBuilder.builder().response(true).data(new JSONArray("[222,222]")).status("I am status all around").build();

        System.out.println("HOOLOLO    :    "+responseBuilder.getResponse());
    }

    @Bean
    public RestTemplate getRestTemplate() {
        return new RestTemplate();
    }
}
