package com.dvm.evyap.thingworxrestconsumer.model;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
@ApiModel
public class FactoryInfo {
    private String FactoryName;
    private String FactoryCode;
}
