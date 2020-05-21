package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.text.SimpleDateFormat;
import android.content.SharedPreferences;

public class screen4 extends AppCompatActivity {


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

    }


public void addTime(View view){
    Intent intent = new Intent(this, screen5.class);
    startActivity(intent);

}//end of addtime

    public void return4(View v){
        Intent intent1 = new Intent(this,MainActivity.class);
        startActivity(intent1);
        

    }//end return4


}
