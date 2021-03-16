package com.dvm.evyap.thingworxrestconsumer.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@ApiModel
@Data
public class FactoryResponseInfo {
    @ApiModelProperty(dataType="List", value = "data")
    List<FactoryInfo> data ;
    String status;
    boolean response;

}
