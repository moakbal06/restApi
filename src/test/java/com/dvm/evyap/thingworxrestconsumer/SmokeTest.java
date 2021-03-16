package com.dvm.evyap.thingworxrestconsumer;

import com.dvm.evyap.thingworxrestconsumer.controller.LoginRestController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class SmokeTest {

    @Autowired
    private LoginRestController loginRestController;

    @Test
    public void contextLoads() throws Exception{

        assertThat(loginRestController).isNotNull();

    }
}
