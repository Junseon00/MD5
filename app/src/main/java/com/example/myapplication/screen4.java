package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlarmManager;
import android.app.Notification;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.text.SimpleDateFormat;
import android.content.SharedPreferences;

import java.util.Calendar;
import java.util.List;

import android.app.AlarmManager;
public class screen4 extends AppCompatActivity {

Switch sw;
private AlarmManager alarmManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen4);


        // 리사이클러뷰에 표시할 데이터 리스트 생성.
        ArrayList<String> list = new ArrayList<>();

        ////test용
        //for (int i=0; i<100; i++) {
        //    list.add(String.format("TEXT %d", i)) ;
        //}
        SharedPreferences pref = getSharedPreferences("timeFile", MODE_PRIVATE);
        //key에 저장된 값이 있는지 확인. 아무값도 들어있지 않으면 ""를 반환
        String text = pref.getString("timeselected","");
        if(text == ""){
            SharedPreferences.Editor editor = pref.edit();
            editor.putString("timeselected", "yes"); //파일이 수정된 적 있는지 확인함
            editor.putString("time1", "09:00");
            editor.putString("time2", "12:00");
            editor.putString("time3", "17:00");

            editor.commit();
        }

        //preference에 저장된 시간 불러오기
        String temp = pref.getString("time1","");
        int i=1;
        //if()//전부 다 삭제한 상태
        while(temp != ""){
            list.add(temp);
            i +=1;
            temp = pref.getString("time"+i,"");
        }

        // 리사이클러뷰에 LinearLayoutManager 객체 지정.
        RecyclerView recyclerView = findViewById(R.id.recycler1) ;
        recyclerView.setLayoutManager(new LinearLayoutManager(this)) ;

        // 리사이클러뷰에 SimpleTextAdapter 객체 지정.
        SimpleTextAdapter adapter = new SimpleTextAdapter(list) ;
        recyclerView.setAdapter(adapter) ;



        /////////////////스위치 관련 연산//////////////////////
        sw =(Switch)findViewById(R.id.switch1);
        TextView tv = (TextView)findViewById(R.id.tv);
        ///실행 유무 표시
        String set = pref.getString("alarm","");
        if(set == "off"){
            tv.setText("알람이 실행중이 아닙니다.");
            sw.setChecked(false);
        }
        else if(set == "on"){
            tv.setText("알람이 실행중입니다.");
            sw.setChecked(true);
        }
        else {
            tv.setText("알람이 실행중이 아닙니다.");
            sw.setChecked(false);
        }
        //리스너 설정 (서비스 실행 , 종료 기능)
        sw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CheckState();
            }
        });
        ////////////////////////////////////////////////////


    }


public void addTime(View view){
        ///////////////알람을 끈 다음에만 실행 가능하도록 수정하기 ///////////////////////
    // /삭제 연산도 마찬가지///////////////////

    Intent intent = new Intent(this, screen5.class);
    startActivity(intent);

}//end of addtime

    public void return4(View v){
        Intent intent1 = new Intent(this,MainActivity.class);
        startActivity(intent1);


    }//end return4

//스위치 관련 코드
    private void CheckState() {
        Intent intent = new Intent(this, Alarm_Receiver.class);
        PendingIntent pIntent = PendingIntent.getBroadcast(this, 0, intent, 0);
        TextView tv = (TextView)findViewById(R.id.tv);

        if(sw.isChecked()) {
            ///////text, shared preference설정////////
            tv.setText("알람이 실행중입니다.");
            SharedPreferences pref = getSharedPreferences("timeFile", MODE_PRIVATE);
            //key에 저장된 값이 있는지 확인. 아무값도 들어있지 않으면 ""를 반환
            String text = pref.getString("alarm","");
            SharedPreferences.Editor editor = pref.edit();
            if(text == ""){
                //알람 on을 넣어준다.
                editor.putString("alarm","on");
                editor.commit();
            }
            else if(text == "off"){
                editor.remove("alarm");
                editor.putString("alarm","on");
                editor.commit();
            }
            //////////////////////////////////////////

            //서비스 실행
            alarmset();

        }
        else{ //알람이 꺼져있을 때
            SharedPreferences pref = getSharedPreferences("timeFile", MODE_PRIVATE);
            SharedPreferences.Editor editor = pref.edit();
            tv.setText("알람이 실행중이 아닙니다.");
            editor.remove("alarm");
            editor.putString("alarm","off");
            editor.commit();
            //여기에는 서비스 종료 코드만 있으면 된다.
            Toast.makeText(screen4.this,"Alarm 종료",Toast.LENGTH_SHORT).show();
            // 알람매니저 취소
            unalarm();


        }
    }


public void alarmset(){
        //알람을 실행시키는 코드

    AlarmManager alarm_manager;
    // 알람매니저 설정
    alarm_manager = (AlarmManager) getSystemService(ALARM_SERVICE);

    //broadcast receiver 인텐트 설정
    final Intent my_intent = new Intent(this, Alarm_Receiver.class);

    //모든 시간을 다 불러와 알람에 설정
    int i=1;
    SharedPreferences pref = getSharedPreferences("timeFile", MODE_PRIVATE);
    String times= pref.getString("time1","");

    while(times != ""){
        //매번 다른 알람 시간을 설정하기 위해 ID(매번 다른 정수 등록) = i
        PendingIntent pendingIntent = PendingIntent.getBroadcast(getApplicationContext(), i , my_intent, PendingIntent.FLAG_UPDATE_CURRENT);

        int hour;
        int minute;

        //시간을 :를 구분자로 가져온다.
        List<String> tarray= Arrays.asList(times.split(":"));
        hour=Integer.parseInt(tarray.get(0));
        minute = Integer.parseInt(tarray.get(1));


        //캘린더에 시간, 분, 초, 밀리초 설정
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, hour);
        calendar.set(Calendar.MINUTE, minute);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);


        // 알람셋팅
        // 지정한 시간에 매일 알림
        alarm_manager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(),  AlarmManager.INTERVAL_DAY, pendingIntent);

        //다음 시간 불러오기
        i+=1;
        times = pref.getString("time"+i,"");
    }



}


public void unalarm(){
        AlarmManager alarm_manager;
        // 알람매니저 설정
        alarm_manager = (AlarmManager) getSystemService(ALARM_SERVICE);
        Intent intent = new Intent(this, Alarm_Receiver.class);
        PendingIntent pIntent = PendingIntent.getBroadcast(this, 0, intent, 0);
        alarm_manager.cancel(pIntent);
    }//알람 해제

}
