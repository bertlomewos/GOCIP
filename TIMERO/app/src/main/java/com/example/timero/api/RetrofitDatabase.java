package com.example.timero.api;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitDatabase {

    private static final String baseUrl = "http://192.168.3.221:5219/";
    static Retrofit retrofitDatabase;

    public static Retrofit getInstance(){
        if(retrofitDatabase == null){
            retrofitDatabase = new Retrofit.Builder()
                    .baseUrl(baseUrl)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofitDatabase;
    }
}