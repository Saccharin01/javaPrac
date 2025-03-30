package LoAExchange.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import java.util.List;

@Getter
@NoArgsConstructor
public class MarketResponse {

    @JsonProperty("PageNo")
    private int pageNo;

    @JsonProperty("PageSize")
    private int pageSize;

    @JsonProperty("TotalCount")
    private int totalCount;

    @JsonProperty("Items")
    private List<ItemDTO> items;
}