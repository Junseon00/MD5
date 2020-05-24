package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class screen2 extends AppCompatActivity {

    Button bt1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen2);


        //실수로 회원가입 창에 들어왔을 때 뒤로가는 용도
        bt1 = findViewById(R.id.bt9);


        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                Intent intent = new Intent(screen2.this, screen1.class);
                startActivity(intent);
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
