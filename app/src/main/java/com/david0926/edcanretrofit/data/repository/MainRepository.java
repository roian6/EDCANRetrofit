package com.david0926.edcanretrofit.data.repository;

import com.david0926.edcanretrofit.BuildConfig;
import com.david0926.edcanretrofit.data.model.FoodRequest;
import com.david0926.edcanretrofit.data.model.FoodResponse;
import com.david0926.edcanretrofit.data.network.RetrofitClient;
import com.google.gson.Gson;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainRepository {

    public interface OnGetFoodsSuccess {
        void success(FoodResponse response);
    }

    public interface OnGetFoodsError {
        void error(FoodResponse errorBody);
    }

    public interface OnGetFoodsFailure {
        void failure(Throwable t);
    }

    public static void getFoods(String search, Integer page, OnGetFoodsSuccess onSuccess, OnGetFoodsError onError, OnGetFoodsFailure onFailure) {
        FoodRequest request = FoodRequest.builder()
                .ServiceKey(BuildConfig.API_KEY)
                .pageNo(page.toString())
                .prdlstNm(search)
                .returnType("json")
                .numOfRows(String.valueOf(10))
                .build();

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
                    Gson gson = new Gson();
                    onError.error(gson.fromJson(gson.toJson(response.errorBody()), FoodResponse.class));
                } else onSuccess.success(response.body());
            }

            @Override
            public void onFailure(Call<FoodResponse> call, Throwable t) {
                onFailure.failure(t);
            }
        });
    }
}
