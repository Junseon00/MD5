package com.example.myapplication;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;

public class Net {
    private static Net ourInstance = new Net();

    public static Net getInstance() {
        return ourInstance;
    }

    private Net() {
    }


    //ngrok2 주소 업데이트해줘야함(retroAPI에도)
    private Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("http://13.125.80.169:8000/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    retroAPI memberFactoryIm; //--------------------------------------- B

    public retroAPI getMemberFactory(){ //------------------------ C
        if(memberFactoryIm ==null){
            memberFactoryIm = retrofit.create(retroAPI.class);
        }

        return memberFactoryIm;
    }
}


