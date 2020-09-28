package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

public class Screen11Social extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen11_social);

        //레이아웃을 위에 겹쳐서 올리는 부분
        LayoutInflater inflater = (LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        //레이아웃 객체생성
        LinearLayout ll = (LinearLayout)inflater.inflate(R.layout.emergency, null);
        //레이아웃 배경 투명도 주기
        ll.setBackgroundColor(Color.parseColor("#00000000"));
        //레이아웃 위에 겹치기
        LinearLayout.LayoutParams paramll = new LinearLayout.LayoutParams
                (LinearLayout.LayoutParams.FILL_PARENT,LinearLayout.LayoutParams.FILL_PARENT);
        addContentView(ll, paramll);
        //위에겹친 레이아웃에 온클릭 이벤트주기
        //ll.setOnClickListener(writeListener);



    }

    public void toMain(View v){
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
    }
}