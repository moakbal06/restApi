package com.dvm.evyap.thingworxrestconsumer;

import com.dvm.evyap.thingworxrestconsumer.controller.ReactiveController;
import com.dvm.evyap.thingworxrestconsumer.service.ConsumeWebService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@WebMvcTest(ReactiveController.class)
public class WebLayerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ConsumeWebService service;

    @Test
    public void returnDefault() throws Exception{

        this.mockMvc
                .perform(post("/GetShiftReportReactive")
                .content("{\"sd\":\"11/01/2020 00:00\",\"ed\":\"11/30/2020 00:00\"}"))
                .andDo(print())
                .andExpect(status().isOk());
                //.andExpect(content().string(containsString("Hello, Mock")));
    }
}
