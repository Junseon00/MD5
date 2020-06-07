package com.example.myapplication;

//마이페이지 입니다

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class screen6 extends AppCompatActivity {

    private TextView tv_id, tv_pass;
    private Button Modif, UsedList, QnA;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen6);


        tv_id = findViewById(R.id.tv_id);
        Intent intent = getIntent();
        String userID = intent.getStringExtra("userID");

        tv_id.setText(userID);

        Modif = (Button)findViewById(R.id.Modif);
        Modif.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
//                Intent intent = new Intent(getApplicationContext(),x.class);
//                startActivity(intent);
            }

        });

        UsedList = (Button)findViewById(R.id.UsedList);
        UsedList.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
//                Intent intent = new Intent(getApplicationContext(),x.class);
//                startActivity(intent);
            }

        });

        QnA = (Button)findViewById(R.id.QnA);
        QnA.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
//                Intent intent = new Intent(getApplicationContext(),x.class);
//                startActivity(intent);
            }

        });



    }

}
