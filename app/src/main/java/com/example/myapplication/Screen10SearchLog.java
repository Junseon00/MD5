package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class Screen10SearchLog extends AppCompatActivity {

    TextView noresult;
    LinearLayout ln;
    RecyclerView rv;
    private ArrayList<com.example.myapplication.Log> dataList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen10_search_log);
        rv=(RecyclerView)findViewById(R.id.rv);

        //모든 처방 검색 내역을 불러온다.
        MyDatabaseOpenHelper helper = new MyDatabaseOpenHelper(this);
        SQLiteDatabase db = helper.getWritableDatabase();
        Cursor cursor = db.rawQuery("select _id,picture,taking,pills,date,due,warning,detail from prescription order by _id",null);

        if(cursor.getCount() == 0){
            Log.d("udb screen10","검색 내역이 없음. 빈 db임");
            noresult=(TextView)findViewById(R.id.textView16);
            noresult.setText("검색한 내역이 없습니다.");
            ln=(LinearLayout)findViewById(R.id.ln);
            ln.setVisibility(View.INVISIBLE); //열 안보이게 하기
            rv.setVisibility(View.INVISIBLE); //리사이클러뷰 안보이게 하기

        }
        else{
            Log.d("udb screen10","검색 내역이 있음. 행 수="+cursor.getCount());
            //이제 행을 불러오자.
            dataList = new ArrayList<>();
            while(cursor.moveToNext()){
                int id;
                String date;
                String pills;
                String taking;
                String warn;
                String due; //현재 시간과 비교해 due가 지났다면 taking을 바꿔야한다.

                id = cursor.getInt(0);
                taking=cursor.getString(2);
                pills=cursor.getString(3);
                date=cursor.getString(4);
                due=cursor.getString(5);
                warn=cursor.getString(6);

                dataList.add(new com.example.myapplication.Log(id,taking,pills,date,warn));
                Log.d("udb screen10","log아이템 집어넣음");
            }
            //리사이클러뷰에 어댑터와 매니저 등록
            LinearLayoutManager manager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false);
            rv.setLayoutManager(manager); // LayoutManager 등록
            rv.setAdapter(new LogAdapter(dataList));  // Adapter 등록
            Log.d("udb screen10","리사이클러뷰 추가 완료");


        }





        db.close();

    }

    public void toMain(View v){
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
    }

}
