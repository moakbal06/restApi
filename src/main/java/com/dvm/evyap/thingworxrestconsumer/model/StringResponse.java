package com.dvm.evyap.thingworxrestconsumer.model;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.util.List;


@ApiModel
@Data
public class StringResponse {
    List<String> data ;
    String status;
    boolean response;

}
