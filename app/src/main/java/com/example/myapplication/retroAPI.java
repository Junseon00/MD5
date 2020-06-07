package com.example.myapplication;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface retroAPI {             //--------------------------------- B

    //통신 담당 메소드 구현.
    @GET("myadmin/sql.php?server=1&db=hazefor&table=USER")
    Call<List<User>> login(@Query("userID") String id, @Query("userPassword") String pass);

}
