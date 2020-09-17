package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

public class LogPopup extends Activity {

    TextView id;
    TextView date;
    TextView due;
    TextView taking;
    ImageView pic;
    TextView pills;
    TextView detail; //warning


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_log_popup);

        //리사이클러뷰가 전달한 pos = id를 받기
        //pos는 0부터임
        Intent intent = getIntent();
        int idnum = intent.getExtras().getInt("id")+1;
        id= (TextView)findViewById(R.id.id);
        id.setText(""+idnum);

        //view와 매칭
        date = (TextView)findViewById(R.id.date);
        due = (TextView)findViewById(R.id.due);
        taking = (TextView)findViewById(R.id.taking); //복용 여부
        pic = (ImageView)findViewById(R.id.imageView);
        pills = (TextView)findViewById(R.id.pills);
        detail = (TextView)findViewById(R.id.warning);

        //id를 기준으로 검색 내역을 불러온다.
        String query = "SELECT picture, taking, pills, date, due, warning,detail FROM prescription WHERE _id ="+idnum;
        MyDatabaseOpenHelper helper = new MyDatabaseOpenHelper(this);
        SQLiteDatabase db = helper.getWritableDatabase();
        Cursor cursor = db.rawQuery(query,null);

        Log.d("udb logpopup","dbd에서 id 기반 검색 끝. 모든 열 불러오기");

            //어차피 열은 한개뿐일테니 한번만 반복함
        while(cursor.moveToNext()){
            //이미지는 패스
            date.setText(cursor.getString(3));
            due.setText(cursor.getString(4));
            taking.setText(cursor.getString(1));
            pills.setText(cursor.getString(2));
            String warning =cursor.getString(6);
            if (warning != null){detail.setText(warning);}

        }




    }

    public void close(View v){
        finish();
    }
}