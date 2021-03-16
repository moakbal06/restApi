package com.dvm.evyap.thingworxrestconsumer.model;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.google.gson.annotations.SerializedName;
import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel
public class BandInfo {

    //@SerializedName("Fabrika Adı")
    private String FactoryName;
    //@SerializedName("Fabrika Kodu")
    private String FactoryCode;
    //@SerializedName("Hat Adı")
    private String LineName;
   //@SerializedName("Vardiya OEE")
    private String CurrentShiftOEEStr;
    //@SerializedName("Çalışılan Ürün")
    private String ProductName;
    //@SerializedName("Ürün No")
    private String ProductNumber;
    //@SerializedName("Tarih")
    private Date TimeStamp;
    //@SerializedName("Önceki Fire Miktarı")
    private Double PreviousShiftScrapAmount;
    //@SerializedName("Anlık Makine Firesi")
    private Double CurrentShiftScrapAmount;
    //@SerializedName("Önceki Vardiya Üretim")
    private Double PreviousShiftTotalProduction;
    //@SerializedName("Anlık Net Üretim")
    private Double CurrentShiftTotalProduction;
    //@SerializedName("Vardiya OEE(#)")
    private Double CurrentShiftOEE;
    //@SerializedName("Müşteri")
    private String Customer;
    //@SerializedName("Bölge")
    private String Region;
    //@SerializedName("Mevcut Duruş")
    private String CurrentStopReason;
    //@SerializedName("Duruş Süresi")
    private String CurrentStopDurationStr;



}
