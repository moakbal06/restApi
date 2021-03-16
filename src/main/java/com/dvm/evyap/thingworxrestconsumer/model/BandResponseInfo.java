package com.dvm.evyap.thingworxrestconsumer.model;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@ApiModel
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BandResponseInfo {

    BandInfo data;
    String status;
    boolean response;

}
