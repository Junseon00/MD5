package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class screen1 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen1);
        //일단 로그인용으로 썼던 코드 주석처리 해놓음
//

//
//        et_id = findViewById(R.id.et_id);
//        et_pass = findViewById(R.id.et_pass);
//        btn_login = findViewById(R.id.btn_login);
//        btn_register = findViewById(R.id.btn_register);
//
//        // 회원가입 버튼을 클릭 시 수행
//        btn_register.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view){
//                Intent intent = new Intent(screen1.this, screen2.class);
//                startActivity(intent);
//            }
//        });
//
//        btn_login.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view){
//                // EditText에 현재 입력되어있는 값을 get해온다.
//                String userID = et_id.getText().toString();
//                String userPass = et_pass.getText().toString();
//
//                Response.Listener<String> reponseListener = new Response.Listener<String>() {
//                    @Override
//                    public void onResponse(String response) {
//                        try{
//
//                            JSONObject jsonObject = new JSONObject(response);
//                            boolean success = jsonObject.getBoolean("success");
//                            if(success){ //로그인에 성공
//                                String userID = jsonObject.getString("userID");
//                                String userPass = jsonObject.getString("userPassword");
//
//                                Toast.makeText(getApplicationContext(),"로그인에 성공했습니다.", Toast.LENGTH_SHORT).show();
//                                Intent intent = new Intent(screen1.this, MainActivity.class);
//                                intent.putExtra("userID", userID);
//                                intent.putExtra("userPass", userPass);
//                                startActivity(intent);
//                            }
//                            else{ //로그인에 실패
//                                Toast.makeText(getApplicationContext(),"로그인에 실패했습니다.", Toast.LENGTH_SHORT).show();
//                                return;
//                            }
//
//                        }
//                        catch (JSONException e){
//                            e.printStackTrace();
//                        }
//                    }
//                };
//
//                LoginRequest loginRequest = new LoginRequest(userID, userPass, reponseListener);
//                RequestQueue queue = Volley.newRequestQueue(LoginActivity.this);
//                queue.add(loginRequest);
//            }
//        });
//    }
    }
}
