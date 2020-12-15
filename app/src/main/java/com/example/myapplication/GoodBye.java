package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class GoodBye extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goodbye);
    }

    public void toHome(View v){
        Intent intent = new Intent(this, screen1login.class);
        startActivity(intent);
    }
}
