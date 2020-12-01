package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.widget.Toast;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Mission1 extends Activity {

    private final String TAG = getClass().getSimpleName();
    private final String BASE_URL = "https://73b8ab351527.ngrok.io/";
    retroAPI missionApi;

    //미션에 필요한 필드값들
    int miID;
    String idA;
    String idB;
    String logM;
    boolean auto;
    String answer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //타이틀바 없애기
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mission1);

        initMyAPI(BASE_URL);
    }



    private void initMyAPI(String baseUrl) {

        //서버에 대한 로그를 더 자세히 얻기 위한 코드
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();

        Log.d("서버센드", "initMyAPI : " + baseUrl);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();

        missionApi = retrofit.create(retroAPI.class);
    }


    public void send(){
        DB_mission mission = new DB_mission(miID, idA, idB, logM, auto, answer);
        Call<DB_mission> call1 = missionApi.post_missions(mission);
        call1.enqueue(new Callback<DB_mission>() {
            @Override
            public void onResponse(Call<DB_mission> call, Response<DB_mission> response) {
                if (!response.isSuccessful()) {
                    android.util.Log.d(TAG, "Status Code : " + response.code());
                    Toast.makeText(getApplicationContext(), " 실패", Toast.LENGTH_SHORT).show();
                }

                Toast.makeText(getApplicationContext(), "미션이 등록되었습니다!", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<DB_mission> call, Throwable t) {

                Toast.makeText(getApplicationContext(), "error1 연결은 됐지만...", Toast.LENGTH_SHORT).show();

            }
        });
    }



    //onclick?


}