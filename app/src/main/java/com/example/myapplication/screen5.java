package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TimePicker;
import android.widget.Toast;
import java.util.Calendar;

public class screen5 extends AppCompatActivity {

    TimePicker timepicker;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen5);
        timepicker=(TimePicker) findViewById(R.id.timePicker);



    }


public void add(View v){

    //가장 마지막으로 저장된 index가 무엇인지 측정
    SharedPreferences pref = getSharedPreferences("timeFile", MODE_PRIVATE);
    int i = 1;
    String temp = pref.getString("time1","");
    //time + i가 비었을때 까지 진행
    while(temp != ""){
        i +=1;
        temp = pref.getString("time"+i,"");
    }


    SharedPreferences.Editor editor = pref.edit();
    //editor.putString("timeselected", "yes"); //파일이 수정된 적 있는지 확인함
    //이거 할 필요가 있는가? 전 페이지에 최초에 들어가보았으면 당연히 표시될 것.

    //timepicker에서 시간 받아와서 time 만들기
    String time = "";
   int getHour = 0;
   int getMinute = 0;


    //호환성 다룰때 추가 할 코드.
    if(Build.VERSION.SDK_INT < 23)
    {
        getHour = timepicker.getCurrentHour();
        getMinute = timepicker.getCurrentMinute();
    }
    else
    {
        getHour = timepicker.getHour();
        getMinute = timepicker.getMinute();
    }

    time= getHour + ":" + getMinute;
    editor.putString("time"+i, time);
    editor.commit();

    //저장이 끝나고 되돌아간다.
    Intent intent = new Intent(this, screen4.class);
    startActivity(intent);

}


public void return5(View v){
        Intent intent1 = new Intent(this, screen4.class);
        startActivity(intent1);


}



}
