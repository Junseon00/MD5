package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class screen3 extends AppCompatActivity {

    private Button bt1, bt2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen3);

        bt1 = findViewById(R.id.btn1);
        bt2 = findViewById(R.id.btn2);

//        bt1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view){
//                Intent intent = new Intent(X.this, X.class);
//                startActivity(intent);
//            }
//        });

//          bt2.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view){
//                Intent intent = new Intent(X.this, X.class);
//                startActivity(intent);
//            }
//        });



    }
}
