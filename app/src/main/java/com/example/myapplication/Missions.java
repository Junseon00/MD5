package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;

import java.util.Objects;

public class Missions extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_missions);
        Objects.requireNonNull(getWindow()).setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        // dialog 배경 투명하게
    }

    public void back(View view) {
        Intent intent1 = new Intent(this, Screen11Follow.class);
        startActivity(intent1);
    }

    public void finish(View view){
        Intent intent2 = new Intent(this, dolvon_popup.class);
        startActivity(intent2);
    }



}