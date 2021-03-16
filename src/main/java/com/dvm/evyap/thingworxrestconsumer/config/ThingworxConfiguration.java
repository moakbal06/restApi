package com.dvm.evyap.thingworxrestconsumer.config;

import com.dvm.evyap.thingworxrestconsumer.model.ThingworxInfo;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ThingworxConfiguration {

    @Bean
    @ConfigurationProperties("thingworx")
    public ThingworxInfo thingworxInfo() {
        return new ThingworxInfo();
    }


}
