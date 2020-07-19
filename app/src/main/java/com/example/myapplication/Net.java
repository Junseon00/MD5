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


    //ngrok2

    private Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("https://2c5ebddb476b.ngrok.io/")
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


