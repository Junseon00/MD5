package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class screen2register extends AppCompatActivity implements View.OnClickListener {
//
//    OkHttpClient client = new OkHttpClient.Builder().addInterceptor(httpLoggingInterceptor()).build();
//


    //이름 형식이랑 넣어야함


    private final String TAG = getClass().getSimpleName();

    private final String BASE_URL = "http://93d88262859b.ngrok.io/";
    //private final String BASE_URL = "http://13.125.80.169:8000/";
    retroAPI regApi;

    Intent intent = new Intent(this, screen1login.class);

    Button btn_register;
    private EditText et_id, et_pass, et_phone, et_birth;

    public void home(View v) {
        Intent intent = new Intent(this,screen1login.class );
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen2);




        //실수로 회원가입 창에 들어왔을 때 뒤로가는 용도
        et_id = findViewById(R.id.et_id);
        et_pass = findViewById(R.id.et_pass);
        et_phone = findViewById(R.id.et_phone);
        et_birth = findViewById(R.id.et_birth);

        btn_register = findViewById(R.id.btn_register);
        btn_register.setOnClickListener(this);


        initMyAPI(BASE_URL);
    }

//    private HttpLoggingInterceptor httpLoggingInterceptor(){
//
//        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
//            @Override
//            public void log(String message) {
//                android.util.Log.e("MyGitHubData :", message + "");
//            }
//        });
//
//        return interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
//    }

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

        regApi = retrofit.create(retroAPI.class);
    }

    public void onClick(View v){

        if(v==btn_register){
            Intent intent = new Intent(screen2register.this, screen1login.class);
            startActivity(intent);
            Toast.makeText(getApplicationContext(), "KimEwha"+" 님, 가입을 환영합니다!", Toast.LENGTH_SHORT).show();

        }


    }

    /*
    public void onClick(View v) {
                if (v == btn_register) {
                    final String id = et_id.getText().toString();
                    final String pass = et_pass.getText().toString();
                    final String phone = et_phone.getText().toString();
                    final String birth = et_birth.getText().toString();

                    if (TextUtils.isEmpty(id)) {
                        et_id.setError("아이디를 써주세요");
                    }
                    if(id.length()<8){
                        et_id.setError("아이디는 8자 이상으로 써주세요");
                    }


                    if (TextUtils.isEmpty(pass)) {
                        et_pass.setError("비밀번호를 써주세요");
                    }
                    if(pass.length()<8||pass.length()>20){
                        et_id.setError("비밀번호는 8자 이상, 20자 이하로 써주세요");
                    }

                    byte[] passCheck = new byte[pass.length()];
                    passCheck = pass.getBytes();

                    int cnt=0;

            boolean upper = false;
            boolean down = false;
            boolean special = false;
            boolean num = false;
            boolean passError = false;


            for(int i=0; i<passCheck.length ; i++){


                if(65 <= passCheck[i] && passCheck[i] <= 90){
                    //대문자가 있는지 검사
                    upper = true;
                }else if(97 <= passCheck[i] && passCheck[i] <= 122){
                    //소문자 검사
                    down = true;
                }else if((21<= passCheck[i] && passCheck[i] <= 26)||(63<= passCheck[i] && passCheck[i] <= 64)||(passCheck[i] == 94)){
                    //특수문자 검사
                    //! " # $ % & ? @ ^ 만 허용
                    special = true;

                }else if(30<= passCheck[i] && passCheck[i] <= 39){
                    //숫자가 있는지 검사
                    num = true;

                }else{
                    //다른 문자의 경우
                    passError = true;
                }

            }

            if(upper==true){
                cnt++;
            }
            if(down==true){
                cnt++;
            }
            if(special==true){
                cnt++;
            }
            if(num==true){
                cnt++;
            }

            if(cnt<3||passError==true){
                et_pass.setError("비밀번호는 영문 대소문자, 특수문자(!\"#$%^@^), 숫자 중 3개 이상을 조합하여 8자 이상으로 써주세요");
            }









            if (TextUtils.isEmpty(phone)) {
                et_phone.setError("전화번호를 써주세요");
            }
            if (TextUtils.isEmpty(birth)) {
                et_birth.setError("생일을 써주세요");
            }

            if (!TextUtils.isEmpty(id) && !TextUtils.isEmpty(pass) && !TextUtils.isEmpty(phone) && !TextUtils.isEmpty(birth)) {
                User user = new User(id, pass, "11111", phone, "1111-11-11");
                Call<User> call1 = regApi.regist_logs(user);
                call1.enqueue((new Callback<User>() {
                    @Override
                    public void onResponse(Call<User> call, Response<User> response) {
                        if (!response.isSuccessful()) {
                            android.util.Log.d(TAG, "Status Code : " + response.code());
                            Toast.makeText(getApplicationContext(), " 실패", Toast.LENGTH_SHORT).show();
                            return;
                        }

                        User user1 = response.body();

                        String content = "";
                        content += "Id: " + user1.getId() + "\n";
                        Intent intent = new Intent(screen2register.this, screen1login.class);
                        startActivity(intent);
                        Toast.makeText(getApplicationContext(), content+" 님, 환영합니다!", Toast.LENGTH_SHORT).show();

                    }

                    @Override
                    public void onFailure(Call<User> call, Throwable t) {

                        Toast.makeText(getApplicationContext(), "error1 연결은 됐지만...", Toast.LENGTH_SHORT).show();

                    }
                }));

            }







        }
    }

    */
}




        //회원가입 버튼을 누르면
//        btn_register.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                // EditText에 현재 입력되어있는 값을 get해온다.
//
//
//                final String id = et_id.getText().toString();
//                final String pass = et_pass.getText().toString();
//                final String phone = et_phone.getText().toString();
//                final String birth = et_birth.getText().toString();
//
//
//
//                if(TextUtils.isEmpty(id)){
//                    et_id.setError("아이디를 써주세요");
//                }
//                if(TextUtils.isEmpty(pass)){
//                    et_pass.setError("비밀번호를 써주세요");
//                }
//                if(TextUtils.isEmpty(phone)){
//                    et_phone.setError("전화번호를 써주세요");
//                }
//                if(TextUtils.isEmpty(birth)){
//                    et_birth.setError("생일을 써주세요");
//                }
//
//
//                if(!TextUtils.isEmpty(id) && !TextUtils.isEmpty(pass) && !TextUtils.isEmpty(phone) && !TextUtils.isEmpty(birth) ) {
//                    Call<List<User>> call1 = regApi.regist_logs(id, pass, phone, birth);
//                    call1.enqueue((new Callback<List<User>>() {
//                        @Override
//                        public void onResponse(Call<List<User>> call, Response<List<User>> response) {
//                            List<User> user1 = response.body();
//
//                            Toast.makeText(getApplicationContext(), user1.toString()+" 성공했습니다", Toast.LENGTH_SHORT).show();
//                        }
//
//                        @Override
//                        public void onFailure(Call<List<User>> call, Throwable t) {
//
//                            Toast.makeText(getApplicationContext(),"error1 연결은 됐지만...", Toast.LENGTH_SHORT).show();
//
//                        }
//                    }));
//
//                }



//
//                    Call<List<User>> res = Net.getInstance().getMemberFactory().signUp(new User());



//                    User user = new User(id,pass,phone,birth);
//                    Call<User> call1 = retroapi.createUser(user);
//                    call1.enqueue((new Callback<User>() {
//                        @Override
//                        public void onResponse(Call<User> call, Response<User> response) {
//                            User user1 = response.body();
//
//                            Toast.makeText(getApplicationContext(), user1.id+" "+user1.pw, Toast.LENGTH_SHORT).show();
//                        }
//
//                        @Override
//                        public void onFailure(Call<User> call, Throwable t) {
//
//                            Toast.makeText(getApplicationContext(),"error1 연결은 됐지만...", Toast.LENGTH_SHORT).show();
//
//                        }
//                    }));
//
//
//
//
//            }
//        });
//
//
//
//
//
//
//
//    }
//}
