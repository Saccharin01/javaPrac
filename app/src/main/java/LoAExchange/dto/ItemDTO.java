// ItemDTO.java
package LoAExchange.dto;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ItemDTO {

    @JsonProperty("Id")
    private long id;

    @JsonProperty("Name")
    private String name;

    @JsonProperty("Grade")
    private String grade;

    @JsonProperty("Icon")
    private String icon;

    @JsonProperty("BundleCount")
    private int bundleCount;

    @JsonProperty("TradeRemainCount")
    private int tradeRemainCount;

    @JsonProperty("YDayAvgPrice")
    private double YDayAvgPrice;

    @JsonProperty("RecentPrice")
    private int recentPrice;

    @JsonProperty("CurrentMinPrice")
    private int currentMinPrice;

    @JsonGetter("YDayAvgPrice")
    public double getYDayAvgPrice() {
        return YDayAvgPrice;
    }
}