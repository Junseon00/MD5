package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class screen2register extends AppCompatActivity {

    retroAPI retroapi;

    Button bt1,  btn_register;
    private EditText et_id, et_pass, et_phone, et_birth;

    public void home(View v){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen2);

        retroapi = Net.getInstance().getMemberFactory();


        //실수로 회원가입 창에 들어왔을 때 뒤로가는 용도
        et_id = findViewById(R.id.et_id);
        et_pass = findViewById(R.id.et_pass);
        et_phone = findViewById(R.id.et_phone);
        et_birth = findViewById(R.id.et_birth);

        btn_register = findViewById(R.id.btn_register);


        //홈으로 가기


        //회원가입 버튼을 누르면
        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // EditText에 현재 입력되어있는 값을 get해온다.


                final String id = et_id.getText().toString();
                final String pass = et_pass.getText().toString();
                final String phone = et_phone.getText().toString();
                final String birth = et_birth.getText().toString();



                if(TextUtils.isEmpty(id)){
                    et_id.setError("아이디를 써주세요");
                }
                if(TextUtils.isEmpty(pass)){
                    et_pass.setError("비밀번호를 써주세요");
                }
                if(TextUtils.isEmpty(phone)){
                    et_phone.setError("전화번호를 써주세요");
                }
                if(TextUtils.isEmpty(birth)){
                    et_birth.setError("생일을 써주세요");
                }


                if(!TextUtils.isEmpty(id) && !TextUtils.isEmpty(pass) && !TextUtils.isEmpty(phone) && !TextUtils.isEmpty(birth) ) {

//
//                    Call<List<User>> res = Net.getInstance().getMemberFactory().signUp(new User());



                    User user = new User(id,pass,phone,birth);
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
