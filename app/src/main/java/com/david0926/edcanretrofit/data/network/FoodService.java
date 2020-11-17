package com.david0926.edcanretrofit.data.network;

import com.david0926.edcanretrofit.data.model.FoodResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface FoodService {

    @GET("/B553748/CertImgListService/getCertImgListService")
    Call<FoodResponse> getFoods(
            @Query("ServiceKey") String ServiceKey,
            @Query("prdlstReportNo") String prdlstReportNo,
            @Query("prdlstNm") String prdlstNm,
            @Query("returnType") String returnType,
            @Query("pageNo") String pageNo,
            @Query("numOfRows") String numOfRows
    );
}
