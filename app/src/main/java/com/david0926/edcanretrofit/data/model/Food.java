package com.david0926.edcanretrofit.data.model;

import java.io.Serializable;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@Builder
@ToString
public class Food implements Serializable {
    private String manufacture;
    private String rnum;
    private String prdkind;
    private String productGb;
    private String rawmtrl;
    private String imgurl1;
    private String imgurl2;
    private String prdlstReportNo;
    private String prdkindstate;
    private String allergy;
    private String prdlstNm;
}
