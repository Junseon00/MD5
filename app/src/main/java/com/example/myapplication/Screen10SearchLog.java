package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class Screen10SearchLog extends AppCompatActivity {

    TextView noresult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen10_search_log);

        //모든 처방 검색 내역을 불러온다.
        MyDatabaseOpenHelper helper = new MyDatabaseOpenHelper(this);
        SQLiteDatabase db = helper.getWritableDatabase();
        Cursor cursor = db.rawQuery("select _id,picture,taking,pills,date,due,warning,detail from prescription order by _id",null);

        if(cursor.getCount() == 0){
            Log.d("udb screen10","검색 내역이 없음. 빈 db임");
            noresult=(TextView)findViewById(R.id.textView16);
            noresult.setText("검색한 내역이 없습니다.");
        }
        else{
            Log.d("udb screen10","검색 내역이 있음. 행 수="+cursor.getCount());
        }
        db.close();

    }

    public void toMain(View v){
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
    }

}
