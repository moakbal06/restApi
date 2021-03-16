package com.dvm.evyap.thingworxrestconsumer.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@ApiModel
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LineResponseInfo {
    @ApiModelProperty(dataType="List", value = "data")
    List<LineInfo> data ;
    String status;
    boolean response;

}
