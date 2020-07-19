package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class screen2 extends AppCompatActivity {

    Button bt1,  btn_register;
    private EditText et_id, et_pass, et_name, et_email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen2);


        //실수로 회원가입 창에 들어왔을 때 뒤로가는 용도
        bt1 = findViewById(R.id.bt9);
        et_id = findViewById(R.id.et_id);
        et_pass = findViewById(R.id.et_pass);
        et_name = findViewById(R.id.et_name);
        et_email = findViewById(R.id.et_email);

        btn_register = findViewById(R.id.btn_register);


        //뒤로 가기
        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                Intent intent = new Intent(screen2.this, screen1.class);
                startActivity(intent);
            }
        });

        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // EditText에 현재 입력되어있는 값을 get해온다.


                final String id = et_id.getText().toString();
                final String pass = et_pass.getText().toString();
                final String name = et_name.getText().toString();
                final String email = et_email.getText().toString();



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
                                List<User> users = response.body();

                                String rid = users.get(0).toString();
                                String rpass = users.get(1).toString();

                                Toast.makeText(getApplicationContext(),"good"+response.body().get(0).toString(), Toast.LENGTH_SHORT).show();

                                if(id.equals(rid)){
                                    if(pass.equals(rpass)){
                                        Intent intent= new Intent(screen2.this, MainActivity.class);
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





        //참고용으로 예전 회원가입때 썼던 코드 달아놓을게요 저희건 좀 다를거예요


//        // 서버 url 설정
//        final static private String URL = "http://hazefor.dothome.co.kr/Register.php";
//        private Map<String, String> map;
//
//    public RegisterRequest(String userID, String userPassword, String userName, int userAge, Response.Listener<String> listener){
//            super(Method.POST, URL, listener, null);
//            map = new HashMap<>();
//            map.put("userID", userID);
//            map.put("userPassword", userPassword);
//            map.put("userName", userName);
//            map.put("userAge", userAge + "");
//
//        }
//
//        @Override
//        protected Map<String, String> getParams() throws AuthFailureError{
//            return map;
//        }


    }
}
