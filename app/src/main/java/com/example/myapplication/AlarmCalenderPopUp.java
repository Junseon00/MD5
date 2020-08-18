package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

public class AlarmCalenderPopUp extends Activity {

    TextView title;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_alarm_calender_pop_up);

        title = (TextView)findViewById(R.id.alarmlist);


        //데이터 가져오기
        Intent intent = getIntent();
        String date = intent.getStringExtra("date");

        //타이틀 바꾸기
        title.setText(date+"의 약품 복용 기록");


    }

    public void close(View v){ //팝업 종료
        finish();
    }
}