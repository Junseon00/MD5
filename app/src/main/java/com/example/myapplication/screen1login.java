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

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class screen1login extends AppCompatActivity implements View.OnClickListener {


    private final String TAG = getClass().getSimpleName();

    private final String BASE_URL = "http://13.125.80.169:8000/";
    private retroAPI logAPI;

    //http://13.125.80.169:8000/ 메모용 url
    private EditText et_id, et_pass;
    private Button btn_login, btn_register;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen1);

        et_id = findViewById(R.id.et_id);
        et_pass = findViewById(R.id.et_pass);
        btn_login = findViewById(R.id.btn_login);
        btn_login.setOnClickListener(this);
        btn_register = findViewById(R.id.btn_register);


        //회원가입으로 넘어가기
        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(screen1login.this, screen2register.class);
                startActivity(intent);
            }
        });

        initMyAPI(BASE_URL);

    }

    private void initMyAPI(String baseUrl){

        Log.d(TAG,"initMyAPI : " + baseUrl);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        logAPI = retrofit.create(retroAPI.class);
    }


    public void onClick(View v){

        if(v==btn_login){
            Intent intent = new Intent(screen1login.this, MainActivity.class);
            startActivity(intent);
            Toast.makeText(getApplicationContext(), "KimEwha"+" 님, 오늘 하루도 건강하신가요?", Toast.LENGTH_SHORT).show();
        }

    }

//    public void onClick(View v) {
//        if (v == btn_login) {
//            final String id = et_id.getText().toString();
//            final String pass = et_pass.getText().toString();
//
//            if (TextUtils.isEmpty(id)) {
//                et_id.setError("id error");
//            }
//            if (TextUtils.isEmpty(pass)) {
//                et_pass.setError("pwpw error pw");
//            }
//
//            if (!TextUtils.isEmpty(id) && !TextUtils.isEmpty(pass)) {
//                Call<List<User>> getCall = logAPI.get_logs();
//                getCall.enqueue(new Callback<List<User>>() {
//                    @Override
//                    public void onResponse(Call<List<User>> call, Response<List<User>> response) {
//                        if (response.isSuccessful()) {
//                            Toast.makeText(getApplicationContext(), "연결성공", Toast.LENGTH_SHORT).show();
//                            List<User> userList = response.body();
//
//                            for (int i = 0; i < userList.size(); i++) {
//                                User a = userList.get(i);
//                                if (id.equals(a.getId()) && pass.equals(a.getPassword())) {
//                                    Toast.makeText(getApplicationContext(), a.getId()+ "님, 환영합니다!", Toast.LENGTH_SHORT).show();
//                                    i = userList.size() + 1;
//                                    Intent intent = new Intent(screen1login.this, MainActivity.class);
//                                    startActivity(intent);
//
//                                } else if (id.equals(a.getId()) && !pass.equals(a.getPassword())) {
//                                    Toast.makeText(getApplicationContext(), "비밀번호 틀림", Toast.LENGTH_SHORT).show();
//                                    i = userList.size() + 1;
//                                }
//
//
//                                if (i == userList.size() - 1) {
//                                    Toast.makeText(getApplicationContext(), "일치하는 아이디가 없습니다", Toast.LENGTH_SHORT).show();
//                                }
//
//                            }
//
//
//                        } else {
//                            Toast.makeText(getApplicationContext(), "연결은 되었으나", Toast.LENGTH_SHORT).show();
//                            android.util.Log.d(TAG, "Status Code : " + response.code() + response.body());
//                        }
//                    }
//
//                    @Override
//                    public void onFailure(Call<List<User>> call, Throwable t) {
//                        Toast.makeText(getApplicationContext(), "error2", Toast.LENGTH_SHORT).show();
//                        Log.d(TAG, "Fail msg : " + t.getMessage());
//                    }
//
//                });
//
//
//            }
//        }
//
//    }
}






//        btn_login.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                // EditText에 현재 입력되어있는 값을 get해온다.
//
//
//                final String id = et_id.getText().toString();
//                final String pass = et_pass.getText().toString();
//
//
//                if(TextUtils.isEmpty(id)){
//                    et_id.setError("id error");
//                }
//                if(TextUtils.isEmpty(pass)){
//                    et_pass.setError("pwpw error pw");
//                }
//
//                if(!TextUtils.isEmpty(id) && !TextUtils.isEmpty(pass)) {
//
//                    Call<List<User>> res = Net.getInstance().getMemberFactory().user(id, pass);
//                    res.enqueue(new Callback<List<User>>() {
//                        @Override
//                        public void onResponse(Call<List<User>> call, Response<List<User>> response) {
//                            if (response.body() != null) { //null 뿐 아니라 오류 값이 들어올 때도 처리해줘야 함.
//
//
//                                List<User> users = (response.body());
//
//
//
//
//                                //여기서 아이디에 따라 어떤 사용자를 불러올지 정하면 될듯
//                                List<User> userList=users;
//
//                                for(int i=0; i<userList.size(); i++) {
//                                    User a = userList.get(i);
//                                    if (id.equals(a.getId()) && pass.equals(a.getPassword())) {
//                                        Toast.makeText(getApplicationContext(), "good" + a.toString(), Toast.LENGTH_SHORT).show();
//                                        i = userList.size() + 1;
//                                        Intent intent = new Intent(screen1login.this, MainActivity.class);
//                                        startActivity(intent);
//
//                                    } else if (id.equals(a.getId()) && !pass.equals(a.getPassword())) {
//                                        Toast.makeText(getApplicationContext(), "비밀번호 틀림", Toast.LENGTH_SHORT).show();
//                                        i = userList.size() + 1;
//                                    }
//
//
//                                    if(i==userList.size()-1){
//                                        Toast.makeText(getApplicationContext(),"일치하는 아이디가 없습니다", Toast.LENGTH_SHORT).show();
//                                    }
//
//                                }
//
//
//
//
//
//
//
////                                User a = users.get(0);
////
////
////                                String rid = a.getId();
////                                String rpass = a.getPassword();
//////                                Toast.makeText(getApplicationContext(),"이건 rid야"+rid+"이건 users야", Toast.LENGTH_SHORT).show();
////
////
////
////                                Toast.makeText(getApplicationContext(),"good"+response.body().get(0).toString(), Toast.LENGTH_SHORT).show();
////
////                                if(id.equals(rid)){
////                                    if(pass.equals(rpass)){
////                                        Intent intent= new Intent(screen1.this, MainActivity.class);
////                                        startActivity(intent);
////                                    } else{
////                                        Toast.makeText(getApplicationContext(),"비밀번호 틀림", Toast.LENGTH_SHORT).show();
////                                    }
////                                }
//
//
//                            } else {
//                                Toast.makeText(getApplicationContext(),"error1 연결은 되었으나 객체가 비어있음", Toast.LENGTH_SHORT).show();
//                            }
//                        }
//
//                        @Override
//                        public void onFailure(Call<List<User>> call, Throwable t) {
//                            Toast.makeText(getApplicationContext(),"error2", Toast.LENGTH_SHORT).show();
//                        }
//                    });
//                }
//
//
//            }
//        });


//    }
//
//
//
//
//
////    private void login() {
////
////
////
////        String id = et_id.getText().toString();
////        String pass = et_pass.getText().toString();
////
////
////    }
//
//
//}

