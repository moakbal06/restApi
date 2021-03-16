package com.dvm.evyap.thingworxrestconsumer.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.json.JSONArray;

import java.util.List;

@ApiModel
@NoArgsConstructor
@AllArgsConstructor
@Data
public class AllBandResponseInfo {
    @ApiModelProperty(dataType="List", value = "data")
    JSONArray data ;
    String status;
    boolean response;
}
