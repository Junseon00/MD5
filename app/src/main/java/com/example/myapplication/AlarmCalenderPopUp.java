package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

import com.prolificinteractive.materialcalendarview.CalendarDay;

import java.util.ArrayList;

public class AlarmCalenderPopUp extends Activity {

    TextView title;
    RecyclerView recycle;
    TextView nocontext;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_alarm_calender_pop_up);

        //뷰 찾기
        title = (TextView)findViewById(R.id.alarmlist);
        recycle = (RecyclerView)findViewById(R.id.rcycle) ;
        nocontext = (TextView)findViewById(R.id.textView13);
        nocontext.setVisibility(View.INVISIBLE);


        //캘린더에서 선택한 날짜 데이터 가져오기
        Intent intent = getIntent();
        String date = intent.getStringExtra("date");

        //타이틀 바꾸기
        title.setText(date+"의 약품 복용 기록");


        //리사이클러뷰
            //표시할 데이터 저장 리스트 (커스텀인 Patient 데이터 형식이여야함~~)
        ArrayList<Patient> alarmList = new ArrayList<>();
        try{
            //표시할 데이터 불러오기
        MyDatabaseOpenHelper helper = new MyDatabaseOpenHelper(AlarmCalenderPopUp.this);
        SQLiteDatabase db = helper.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT time,memo,taken from patient WHERE date ='"+date.trim()+"';" ,null);
        //order by _id
        while(cursor.moveToNext()){
            String time;
            String memo;
            String yn;

            time = cursor.getString(0);
            memo = cursor.getString(1);
            if( memo == null){ memo = "의약품";}
            yn = cursor.getString(2);
            Log.d("udb alarmcalender popup","when setting taken ="+yn);
            alarmList.add(new Patient(time,memo,yn));
            //Log.d("udb alarmcalenderpopup","리사이클러 뷰 보이기 성공");
        }
            //리사이클러뷰 데이터 전달 및 adapter설정
        recycle.setLayoutManager(new LinearLayoutManager(this)); //매니저 객체지정
        Simple_patientAdapter adapter = new Simple_patientAdapter(alarmList);
        recycle.setAdapter(adapter);}
        catch(Exception e){recycle.setVisibility(View.INVISIBLE);nocontext.setVisibility(View.VISIBLE);}

        if (alarmList == null){nocontext.setVisibility(View.VISIBLE);}


    }

    public void close(View v){ //팝업 종료
        finish();
    }
}