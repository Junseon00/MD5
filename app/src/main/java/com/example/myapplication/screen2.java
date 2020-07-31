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

import com.google.gson.Gson;

import org.json.JSONObject;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class screen2 extends AppCompatActivity {

    retroAPI retroapi;

    Button bt1,  btn_register;
    private EditText et_id, et_pass, et_name, et_email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen2);

        retroapi = Net.getInstance().getMemberFactory();


        //실수로 회원가입 창에 들어왔을 때 뒤로가는 용도
        bt1 = findViewById(R.id.bt9);
        et_id = findViewById(R.id.et_id);
        et_pass = findViewById(R.id.et_pass);
//        et_name = findViewById(R.id.et_name);
//        et_email = findViewById(R.id.et_email);

        btn_register = findViewById(R.id.btn_register);


        //뒤로 가기
        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                Intent intent = new Intent(screen2.this, screen1.class);
                startActivity(intent);
            }
        });


        //회원가입 버튼을 누르면
        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // EditText에 현재 입력되어있는 값을 get해온다.


                final String id = et_id.getText().toString();
                final String pass = et_pass.getText().toString();
//                final String name = et_name.getText().toString();
//                final String email = et_email.getText().toString();



                if(TextUtils.isEmpty(id)){
                    et_id.setError("아이디를 써주세요");
                }
                if(TextUtils.isEmpty(pass)){
                    et_pass.setError("비밀번호를 써주세요");
                }
//                if(TextUtils.isEmpty(name)){
//                    et_id.setError("이름을 써주세요");
//                }
//                if(TextUtils.isEmpty(email)){
//                    et_pass.setError("이메일을 써주세요");
//                }


                if(!TextUtils.isEmpty(id) && !TextUtils.isEmpty(pass)) {

//
//                    Call<List<User>> res = Net.getInstance().getMemberFactory().signUp(new User());



                    User user = new User(id,pass);
                    Call<User> call1 = retroapi.createUser(user);
                    call1.enqueue((new Callback<User>() {
                        @Override
                        public void onResponse(Call<User> call, Response<User> response) {
                            User user1 = response.body();

                            Toast.makeText(getApplicationContext(), user1.id+" "+user1.pw, Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void onFailure(Call<User> call, Throwable t) {

                            Toast.makeText(getApplicationContext(),"error1 연결은 됐지만...", Toast.LENGTH_SHORT).show();

                        }
                    }));


                }

            }
        });







    }
}
