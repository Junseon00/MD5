package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

//내 정보 수정하는 곳
public class screen9mypage extends AppCompatActivity implements View.OnClickListener{


    private final String TAG = getClass().getSimpleName();
    private final String BASE_URL = "http://13.125.80.169:8000/";
    retroAPI changeApi;

    private EditText et_pw, et_phone;
    private Button btn1, btn2, btn3;
    private Button changePw, changePhone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen9mypage);

        et_pw = findViewById(R.id.textmy6);
        et_phone = findViewById(R.id.textmy7);


        initMyAPI(BASE_URL);

    }

    private void initMyAPI(String baseUrl) {

        //서버에 대한 로그를 더 자세히 얻기 위한 코드

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();


        Log.d(TAG, "initMyAPI : " + baseUrl);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();

        changeApi = retrofit.create(retroAPI.class);
    }


    public void home(View v){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }


    public void onClick(View v){}
    /*
    public void onClick(View v){
        if(v == changePw){
            final String pw = et_pw.getText().toString();
            if (TextUtils.isEmpty(pw)) {
                et_pw.setError("비밀번호를 써주세요");
            }

            //if 여기 이후로 비밀번호 제한 걸것..!

            //알맞은 (바꿀) 비밀번호를 입력했을 경우
            if(!TextUtils.isEmpty(pw)){

                //hashed 는 sha로 해줘야함!
                User user = new User(null, pw, pw, null, null);

                //고유 아이디
                Call<User> call1 = changeApi.change_pw("", user);

                call1.enqueue((new Callback<User>(){
                    @Override
                    public void onResponse(Call<User> call, Response<User> response) {
                        if (!response.isSuccessful()){
                            android.util.Log.d(TAG, "Status Code : " + response.code());
                            Toast.makeText(getApplicationContext(), " 실패", Toast.LENGTH_SHORT).show();
                            return;
                        }

                        User userResponse = response.body();


                        //잘 됏는지 보여주기
                        String content = "";
                        content += "Code : " + response.code() + "\n";
                        content += "Id : " + userResponse.getId() + "\n";
                        content += "phone : " + userResponse.getPhone() + "\n";
                        content += "birth : " + userResponse.getbirth() + "\n";

                        Toast.makeText(getApplicationContext(), content+" 로 정보가 바뀌었습니다!", Toast.LENGTH_SHORT).show();

                    }

                    @Override
                    public void onFailure(Call<User> call, Throwable t) {
                        Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }));

            }




        }else if(v == changePhone){

            final String phone = et_phone.getText().toString();
            if (TextUtils.isEmpty(phone)) {
                et_phone.setError("전화번호를 써주세요");
            } //알맞은 전화번호 형식도 말해줘야해


            if(!TextUtils.isEmpty(phone)){

                User user = new User(null, null, null, phone, null);

                //아이디를 인텐트로 받아와야하는데...
                Call<User> call2 = changeApi.change_phone("234r", user);

                call2.enqueue(new Callback<User>() {
                    @Override
                    public void onResponse(Call<User> call, Response<User> response) {
                        if (!response.isSuccessful()){
                            android.util.Log.d(TAG, "Status Code : " + response.code());
                            Toast.makeText(getApplicationContext(), " 실패", Toast.LENGTH_SHORT).show();
                            return;
                        }

                        User userResponse = response.body();

                        String content = "";
                        content += "Code : " + response.code() + "\n";
                        content += "Id : " + userResponse.getId() + "\n";
                        content += "phone : " + userResponse.getPhone() + "\n";
                        content += "birth : " + userResponse.getbirth() + "\n";

                        Toast.makeText(getApplicationContext(), content, Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFailure(Call<User> call, Throwable t) {
                        Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });

            }


        }
    }

    */



}