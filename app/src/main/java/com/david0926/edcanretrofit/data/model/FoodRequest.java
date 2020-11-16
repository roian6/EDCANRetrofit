package com.david0926.edcanretrofit.data.model;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@Builder
@ToString
public class FoodRequest {
    private String ServiceKey;
    private String prdlstReportNo;
    private String prdlstNm;
    private String returnType;
    private String pageNo;
    private String numOfRows;
}
