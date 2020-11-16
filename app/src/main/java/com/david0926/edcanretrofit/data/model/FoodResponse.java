package com.david0926.edcanretrofit.data.model;

import java.util.List;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@Builder
@ToString
public class FoodResponse {
    private String pageNo;
    private String resultCode;
    private String totalCount;
    private List<Food> list;
    private String resultMessage;
    private String numOfRows;
}
