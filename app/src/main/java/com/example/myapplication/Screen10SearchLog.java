package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Screen10SearchLog extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen10_search_log);
    }

    public void toMain(View v){
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
    }

}
