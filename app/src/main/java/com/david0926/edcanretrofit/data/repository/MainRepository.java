package com.david0926.edcanretrofit.data.repository;

import com.david0926.edcanretrofit.BuildConfig;
import com.david0926.edcanretrofit.data.model.FoodRequest;
import com.david0926.edcanretrofit.data.model.FoodResponse;
import com.david0926.edcanretrofit.data.network.RetrofitClient;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainRepository {

    public interface OnGetFoodsSuccess {
        void success(FoodResponse response);
    }

    public interface OnGetFoodsError {
        void error(ResponseBody errorBody);
    }

    public interface OnGetFoodsFailure {
        void failure(Throwable t);
    }

    public static void getFoods(String search, Integer page, OnGetFoodsSuccess onSuccess, OnGetFoodsError onError, OnGetFoodsFailure onFailure) {
        FoodRequest request = null;
        try {
            request = FoodRequest.builder()
                    .ServiceKey(URLDecoder.decode(BuildConfig.API_KEY, "utf-8"))
                    .pageNo(page.toString())
                    .prdlstNm(search)
                    .returnType("json")
                    .numOfRows(String.valueOf(10))
                    .build();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        RetrofitClient.foodService.getFoods(
                request.getServiceKey(),
                request.getPrdlstReportNo(),
                request.getPrdlstNm(),
                request.getReturnType(),
                request.getPageNo(),
                request.getNumOfRows()
        ).enqueue(new Callback<FoodResponse>() {
            @Override
            public void onResponse(Call<FoodResponse> call, Response<FoodResponse> response) {
                if (!response.isSuccessful()) {
                    onError.error(response.errorBody());
                } else onSuccess.success(response.body());
            }

            @Override
            public void onFailure(Call<FoodResponse> call, Throwable t) {
                onFailure.failure(t);
            }
        });
    }
}
