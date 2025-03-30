package LoAExchange.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import com.fasterxml.jackson.annotation.JsonInclude;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RequestPayload {

    @Builder.Default
    private String sort = "GRADE";

    @Builder.Default
    private int categoryCode = 40000;

    @Builder.Default
    private String itemName = "";

    @Builder.Default
    private int pageNo = 0;

    @Builder.Default
    private String sortCondition = "ASC";


    private Object itemTier = null;
    private String characterClass;
    private String itemGrade;
}