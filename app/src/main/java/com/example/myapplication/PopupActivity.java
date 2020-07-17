package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;

public class PopupActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_popup);

    }

    public void dideat(View v){
        //응답 저장
        savePatientAnswer(true);
        //팝업창 종료
        finish();
    }

    public void didnteat(View v){
        //응답 저장
        savePatientAnswer(false);
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


    public void savePatientAnswer(boolean answer){
        //DB가 없으면 만든다
        MyDatabaseOpenHelper helper = new MyDatabaseOpenHelper(this);
        SQLiteDatabase db = helper.getWritableDatabase();
        String sql = "CREATE TABLE  IF NOT EXISTS patient" +" (_id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "date TEXT," +
                "time TEXT," +
                "memo TEXT," +
                "taken boolean);";;
        db.execSQL(sql);

        //※memo를 적용하지 않을거라면, 해당 시간을 가져온다(이것이 저장되는 시기는 알람발생 시기와 20초밖에 차이 안나서, 다시 가져오지 않아도 된다.)

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
        Log.d("udb 시간 불러 옴","데이터 베이스에 해당 값을 저장하려고 함. date:"+date+" time:"+time+" answer:"+answer);

        try{
            //결과를 저장한다.
            // db.execSQL("insert into patient (date,time,taken) values(" +  date+ "," + time + "," + answer +");");
            db.execSQL("insert into patient (date,time,taken) values(?,?,?);",new Object[]{date,time,answer} );
            db.close();
            //결과 출력
            if(answer){
                Toast.makeText(this,"복용 여부가 저장 되었습니다: "+date+" "+time+" 복용 했음.", Toast.LENGTH_SHORT).show();
            }
            else{
                Toast.makeText(this,"복용 여부가 저장 되었습니다: "+date+" "+time+" 복용 안했음.", Toast.LENGTH_SHORT).show();
            }

        }//end of try
        catch(Exception e){
            Log.d("udb pupup Activity db에러","데이터 베이스에 해당 값을 저장하는데 실패함. date:"+date+" time:"+time+" answer:"+answer);
        }//end of catch




    }


}