package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.TimePicker;
import android.widget.Toast;

import java.lang.reflect.Field;
import java.util.Calendar;

public class screen5 extends AppCompatActivity {

    TimePicker timepicker;
    EditText memo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen5);
        timepicker=(TimePicker) findViewById(R.id.timePicker);
        memo = (EditText)findViewById(R.id.memo);

    }


public void add(View v){

    addTimes();

    //자동으로 알람 재설정? 필요?

    //저장이 끝나고 되돌아간다.
    Intent intent = new Intent(this, screen4.class);
    startActivity(intent);

}


public void return5(View v){
        Intent intent1 = new Intent(this, screen4.class);
        startActivity(intent1);

}


public void addTimes(){
    //timepicker에서 시간 받아와서 time 만들기
    String time = "";
    int getHour = 0;
    int getMinute = 0;
    String name; //약 이름이나 메모저장


    //호환성
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

    //잠깐! 의약품 이름이 있나요?
    memo= (EditText)findViewById(R.id.memo);
    name =memo.getText().toString();

    if ( name.length() == 0 ) {
    name = "의약품"; //default

    }



    //시간과 분을 추가한다. 나중에 메모 공간도 필요하다면 추가하면된다. 칼럼은 만들어 뒀음.
    MyDatabaseOpenHelper helper = new MyDatabaseOpenHelper(this);
    SQLiteDatabase db = helper.getWritableDatabase(); //쓰기
    db.execSQL("insert into times (hour,minute,memo) values(?,?,?)",new String[]{Integer.toString(getHour),Integer.toString(getMinute),name});
    db.close();
    Log.d("udb screen5 실행성공","db query = insert into times (hour,minute,memo) values"+Integer.toString(getHour)+","+
            Integer.toString(getMinute)+","+name);

}





}
