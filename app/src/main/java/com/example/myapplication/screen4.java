package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;
import java.text.DateFormat;
import java.util.Date;
import java.text.SimpleDateFormat;

public class screen4 extends AppCompatActivity {

    Switch sw;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen4);
        sw = (Switch)findViewById(R.id.switch1); //sw= 스위치1
        CheckState();

        //switch 1에 리스너 설정 ㅡ 클릭시 내용 실행.
        sw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CheckState();
            }

        });
    }


    private void CheckState(){

        if(sw.isChecked()) {
            //켜졌을시 할 내용
            //이때 텍스트뷰에 적힌 내용을 다 가져오면, 별도로 디폴트값을 저장할 필요 없음.
            //String strTime = "20:15:40";
            //DateFormat dateFormat = new SimpleDateFormat("hh:mm:ss");
            //Date d = dateFormat.parse(strTime);
        }
        else{
            //꺼졌을시 실행내용
        }
    }


   public void popup1(View v){
        //수정 버튼. 팝업 띄우기.
        Intent intent = new Intent(this, Screen5.class);
        //intent.putExtra("data", "Test Popup");
        //startActivityForResult(intent, 1);
       startActivity(intent); //자료는 못 주고받음

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode==1){
            if(resultCode==RESULT_OK){
                //데이터 받기
                String result = data.getStringExtra("result");
                //txtResult.setText(result);
            }
        }
    }




}
