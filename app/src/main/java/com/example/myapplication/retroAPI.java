package com.example.myapplication;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface retroAPI {


    //ngrok1
    public static final String API_URL = "http://32b7487bcaa5.ngrok.io/";
    //--------------------------------- B

    //통신 담당 메소드 구현.
    @GET("user")
    Call<List<User>> user(@Query("id") String id, @Query("pw") String pass);


}
