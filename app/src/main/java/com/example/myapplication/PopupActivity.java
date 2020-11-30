package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

public class PopupActivity extends Activity {

    TextView memotxt;
    String memo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_popup);
        Objects.requireNonNull(getWindow()).setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        // dialog 배경 투명하게

        //뷰 선언
        memotxt = (TextView)findViewById(R.id.textView14);
        //알람이 울렸던 시간과 분을 가져온다.
        Intent intent = getIntent();
        String hour = intent.getExtras().getString("hour");
        String minute = intent.getExtras().getString("minute");

        MyDatabaseOpenHelper helper = new MyDatabaseOpenHelper(PopupActivity.this);
        SQLiteDatabase db = helper.getWritableDatabase();
        String sql = "SELECT memo from times WHERE hour='"+hour+"' AND minute='"+minute+"'";
        Log.d("udb popupactivicy","다음과 같은 sql로 db쿼리 = "+sql);
        Cursor cursor = db.rawQuery(sql ,null);

        if(cursor != null && cursor.moveToFirst()){
            memo = cursor.getString(0);
            memotxt.setText(memo);
        }
        else { //안되면 minute이 잘못 측정 될 수 있으므로 한번 다시 시도해본다.
            Log.d("udb popup activity","퀄 결과가 없다.");
            int min = Integer.parseInt(minute) +1;
            if (min==60){ //만약 +1 이 정각인 경우.
                min=0;
                int h = Integer.parseInt(hour)+1;
                hour=""+h;
            }

            minute = ""+min;
            sql = "SELECT memo from times WHERE hour='"+hour+"' AND minute='"+minute+"'";
            Log.d("udb popupactivicy","다음과 같은 sql로 db쿼리 = "+sql);
            cursor = db.rawQuery(sql ,null);
            if(cursor != null && cursor.moveToFirst()){
                memo = cursor.getString(0);
                memotxt.setText(memo);
            }
            else{
                Log.d("udb popupactivity","쿼리를 1분 늘려서 해도 소득 없음.");
            }
        }

        db.close();

    }

    public void dideat(View v){
        //응답 저장
        savePatientAnswer("O");
        //팝업창 종료
        finish();
    }

    public void didnteat(View v){
        //응답 저장
        savePatientAnswer("X");
        //팝업창 종료
        finish();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        //바깥레이어 클릭시 안닫히게
        if(event.getAction()==MotionEvent.ACTION_OUTSIDE){
            return false;
        }
        return true;
    }

    @Override
    public void onBackPressed() {
        //안드로이드 백버튼 막기
        return;
    }


    public void savePatientAnswer(String answer){
        //DB가 없으면 만든다
        MyDatabaseOpenHelper helper = new MyDatabaseOpenHelper(this);
        SQLiteDatabase db = helper.getWritableDatabase();
        String sql = "CREATE TABLE  IF NOT EXISTS patient" +" (_id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "date TEXT," +
                "time TEXT," +
                "memo TEXT," +
                "taken TEXT);";;
        db.execSQL(sql);


        //시간을 불러와 설정한다
        String date ="0000/00/00";
        String time ="00:00"; //초기화

        // 현재시간을 msec 으로 구한다.
        long now = System.currentTimeMillis();
        // 현재시간을 date2 변수에 저장한다.
        Date date2 = new Date(now);
        // 시간을 나타냇 포맷을 정한다 ( yyyy/MM/dd 같은 형태로 변형 가능 )
        SimpleDateFormat sdfNow = new SimpleDateFormat("HH:mm");
        // String 형식으로 format을 변환해 time에 저장.
        time = sdfNow.format(date2);

        sdfNow = new SimpleDateFormat("yyyy/MM/dd");
        //String 형식으로 format을 변환해 date에 저장
        date = sdfNow.format(date2);
        //결과 체크
        Log.d("udb 시간 불러 옴","데이터 베이스에 해당 값을 저장하려고 함. date:"+date+" time:"+time+" answer:"+answer+" memo"+memo);

        try{


            db.execSQL("insert into patient (date,time,taken,memo) values(?,?,?,?);",new String[]{date,time,answer,memo} );
            db.close();
            //결과 출력
            if(answer=="O"){
                Toast.makeText(this,"복용 여부가 저장 되었습니다: "+date+" "+time+" 복용 했음. memo="+memo, Toast.LENGTH_SHORT).show();
            }
            else{
                Toast.makeText(this,"복용 여부가 저장 되었습니다: "+date+" "+time+" 복용 안했음. memo="+memo, Toast.LENGTH_SHORT).show();
            }

        }//end of try
        catch(Exception e){
            Log.d("udb pupup Activity db에러","데이터 베이스에 해당 값을 저장하는데 실패함. date:"+date+" time:"+time+" answer:"+answer+" memo:"
            +memo);
        }//end of catch




    }


}