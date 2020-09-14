package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    //retroAPI retroapi;

    // Used to load the 'native-lib' library on application startup.
    static {
        System.loadLibrary("native-lib");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Example of a call to a native method


    }

    /**
     * A native method that is implemented by the 'native-lib' native library,
     * which is packaged with this application.
     */
    public native String stringFromJNI();

    public void alarm(View v){
        Intent intent=new Intent(this,screen4.class );
        startActivity(intent);

    }

    public void calender(View v){
        Intent it = new Intent(this,screen8calender.class);
        startActivity(it);
    }



    public void login(View v){
        Intent inte = new Intent(this, screen1login.class);
        startActivity(inte);
    }

    public void Rx(View v){
        Intent inte = new Intent(this, screen3.class);
        startActivity(inte);
    }



    public void toSearchLog(View v){
        Intent intent = new Intent(this,Screen10SearchLog.class);
        startActivity(intent);
    }


    //**효원 작업중 부분**//

//    Activity page = this;

//    public void logout(View v){
//
//        Intent it = new Intent(page,screen1.class);
//        startActivity(it);
//
//
//// 보류..
////        retroapi = Net.getInstance().getMemberFactory();
////        SharedPreferences prefs = getPreferences(context);
////        SharedPreferences editor = getSharedPreferences(prefs, MODE_PRIVATE).edit();
////        String token = editor.getString("token","");
////        Logout log = new Logout();
////
////        Call<Logout> logout = retroapi.logout(log, token);
////        logout.enqueue(new Callback<Logout>() {
////                           @Override
////                           public void onResponse(Call<Logout> call, Response<Logout> response) {
////
////                               Logout logout1 = response.body();
////
////                               Toast.makeText(getApplicationContext(), "로그아웃", Toast.LENGTH_SHORT).show();
////
////
////                           }
////
////                           @Override
////                           public void onFailure(Call<Logout> call, Throwable t) {
////                               Toast.makeText(getApplicationContext(), "error1 연결은 됐지만...", Toast.LENGTH_SHORT).show();
////                           }
////                       });
//    }

    //***효원 작업부분 끝**//





}

