package com.vector.authorbookcommon.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class BitcoinResponseDto {


    @JsonProperty("price")
    private String price;

    @JsonProperty("timestamp")
    private long timestamp;

    @JsonProperty("24h_price_change")
    private String priceChange24h;

    @JsonProperty("24h_price_change_percent")
    private String priceChangePercent24h;

    @JsonProperty("24h_high")
    private String high24h;

    @JsonProperty("24h_low")
    private String low24h;

    @JsonProperty("24h_volume")
    private String volume24h;
}
