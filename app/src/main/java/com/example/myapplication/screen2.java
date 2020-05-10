package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class screen2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen2);

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
