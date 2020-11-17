package com.david0926.edcanretrofit.data.network;

import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {
    private static final String BASE_URL = "http://apis.data.go.kr/";

    public static Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(new GsonBuilder().setLenient().create()))
            .build();

    public static FoodService foodService = retrofit.create(FoodService.class);
}
