package com.example.myapplication;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;
import java.util.Objects;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Add_friend_search extends AppCompatActivity {

    private TextView mListTv;
    private EditText mEdit;

    private final  String TAG = "친구추가";
    private final String BASE_URL = "https://73b8ab351527.ngrok.io/";
    retroAPI friendApi;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_friend_search);
        Objects.requireNonNull(getWindow()).setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        // dialog 배경 투명하게

        mEdit = findViewById(R.id.searchView);
        mListTv = findViewById(R.id.resultView);
        final String query = mEdit.getText().toString();

        initMyAPI(BASE_URL);
    }

    //retroAPI
    private void initMyAPI(String baseUrl) {

        //서버에 대한 로그를 더 자세히 얻기 위한 코드
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();

        Log.d("친구추가", "initMyAPI : " + baseUrl);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();

        friendApi = retrofit.create(retroAPI.class);
    }


    //검색 버튼을 눌렀을 때
    public void query(View v){

        final String query = mEdit.getText().toString();
        if(TextUtils.isEmpty(query)){
            mEdit.setError("전화번호나 아이디를 입력해 주세요.");
        }else{
            android.util.Log.d(TAG,"GET");
            Call<List<User>> getCall = friendApi.get_users();
            getCall.enqueue(new Callback<List<User>>() {
                @Override
                public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                    if( response.isSuccessful()){
                        Toast.makeText(getApplicationContext(),"연결성공", Toast.LENGTH_SHORT).show();
                        List<User> mList = response.body();
                        String result ="0";
                        for( User item : mList){
                            String name = item.getId();
                            String phone = item.getPhone();

                            if(name.contains(query)){
                                result += "아이디: " + item.getId() + "    전화번호: " + item.getPhone() + "\n\n";
                            }else if(phone.contains(query)){
                                result += "아이디: " + item.getId() + "    전화번호: " + item.getPhone() + "\n\n";
                            }

                        }
                        mListTv.setText(result);
                    }else {
                        Toast.makeText(getApplicationContext(),"연결은 되었으나", Toast.LENGTH_SHORT).show();
                        android.util.Log.d(TAG,"Status Code : " + response.code() + response.body());
                    }
                }

                @Override
                public void onFailure(Call<List<User>> call, Throwable t) {
                    Toast.makeText(getApplicationContext(),"error2", Toast.LENGTH_SHORT).show();
                    Log.d(TAG,"Fail msg : " + t.getMessage());
                }

            });

        }

    }

}
