package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Debug;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import org.json.JSONException;
import org.json.JSONObject;

public class screen1 extends AppCompatActivity {


    private EditText et_id, et_pass;
    private Button btn_login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen1);

        et_id = findViewById(R.id.et_id);
        et_pass = findViewById(R.id.et_pass);
        btn_login = findViewById(R.id.btn_login);

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // EditText에 현재 입력되어있는 값을 get해온다.
                String id = et_id.getText().toString();
                String pass = et_pass.getText().toString();


                if(TextUtils.isEmpty(id)){
                    et_id.setError("아이디를 입력하세요.");
                }
                if(TextUtils.isEmpty(pass)){
                    et_pass.setError("비밀번호를 입력하세요.");
                }

                if(!TextUtils.isEmpty(id) && !TextUtils.isEmpty(pass)) {

                    Call<List<User>> res = Net.getInstance().getMemberFactory().login(id, pass);
                    res.enqueue(new Callback<List<User>>() {
                        @Override
                        public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                            if (response.body() != null) { //null 뿐 아니라 오류 값이 들어올 때도 처리해줘야 함.
                                List<User> users = response.body();
                                Toast.makeText(getApplicationContext(),"Main 통신"+response.body().get(0).toString(), Toast.LENGTH_SHORT).show();

                            } else {
                                Toast.makeText(getApplicationContext(),"Main 통신 실패 1 response 내용이 없음", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<List<User>> call, Throwable t) {
                            Toast.makeText(getApplicationContext(),"Main 통신"+"실패 2 서버 에러", Toast.LENGTH_SHORT).show();
                        }
                    });
                }


            }
        });


    }





//    private void login() {
//
//
//
//        String id = et_id.getText().toString();
//        String pass = et_pass.getText().toString();
//
//
//    }


}

