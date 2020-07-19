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
    private Button btn_login, btn_register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen1);

        et_id = findViewById(R.id.et_id);
        et_pass = findViewById(R.id.et_pass);
        btn_login = findViewById(R.id.btn_login);
        btn_register = findViewById(R.id.btn_register);


        //회원가입으로 넘어가기
        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                Intent intent = new Intent(screen1.this, screen2.class);
                startActivity(intent);
            }
        });




        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // EditText에 현재 입력되어있는 값을 get해온다.


                final String id = et_id.getText().toString();
                final String pass = et_pass.getText().toString();


                if(TextUtils.isEmpty(id)){
                    et_id.setError("id error");
                }
                if(TextUtils.isEmpty(pass)){
                    et_pass.setError("pwpw error pw");
                }

                if(!TextUtils.isEmpty(id) && !TextUtils.isEmpty(pass)) {

                    Call<List<User>> res = Net.getInstance().getMemberFactory().user(id, pass);
                    res.enqueue(new Callback<List<User>>() {
                        @Override
                        public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                            if (response.body() != null) { //null 뿐 아니라 오류 값이 들어올 때도 처리해줘야 함.


                                List<User> users = (response.body());

                                //여기서 아이디에 따라 어떤 사용자를 불러올지 정하면 될듯
                                User a = users.get(0);


                                String rid = a.getId();
                                String rpass = a.getPassword();
//                                Toast.makeText(getApplicationContext(),"이건 rid야"+rid+"이건 users야", Toast.LENGTH_SHORT).show();



                                Toast.makeText(getApplicationContext(),"good"+response.body().get(0).toString(), Toast.LENGTH_SHORT).show();

                                if(id.equals(rid)){
                                    if(pass.equals(rpass)){
                                        Intent intent= new Intent(screen1.this, MainActivity.class);
                                        startActivity(intent);
                                    } else{
                                        Toast.makeText(getApplicationContext(),"비밀번호 틀림", Toast.LENGTH_SHORT).show();
                                    }
                                }


                            } else {
                                Toast.makeText(getApplicationContext(),"error1", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<List<User>> call, Throwable t) {
                            Toast.makeText(getApplicationContext(),"error2", Toast.LENGTH_SHORT).show();
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

