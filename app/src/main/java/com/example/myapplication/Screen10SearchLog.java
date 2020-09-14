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

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

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

                //현재 날짜와 비교해 복용 여부 갱신. 날짜 꼴은 2020-09-14 꼴.
                String[] dbtime = due.split("-");
                long nowt = System.currentTimeMillis(); //오늘
                Date now = new Date(nowt);
                SimpleDateFormat sdfNow = new SimpleDateFormat("yyyy-MM-dd");
                String formatDate = sdfNow.format(now);
                String[] ttime = formatDate.split("-");
                Log.d("udb screen10","현재 날짜:"+ttime[0]+"-"+ttime[1]+"-"+ttime[2]);
                Log.d("udb screen10","만료 날짜:"+dbtime[0]+"-"+dbtime[1]+"-"+dbtime[2]);

                if(taking.equals("복용중")) {
                    Log.d("udb screen10","복용 중이라 만료 날짜를 검사함 ...");
                    if (Integer.parseInt(dbtime[0]) < Integer.parseInt(ttime[0])) {
                        changeTaking(id); taking="복용끝"; Log.d("udb screen10","사유: 년도가 지났음");
                    } else if (dbtime[0].equals(ttime[0]) &&
                            Integer.parseInt(dbtime[1]) < Integer.parseInt(ttime[1])){
                        changeTaking(id); taking="복용끝"; Log.d("udb screen10","사유: 달이 지났음");
                    }else if (dbtime[0].equals(ttime[0])  && dbtime[1].equals(ttime[1]) &&
                            Integer.parseInt(dbtime[2]) < Integer.parseInt(ttime[2])){
                        changeTaking(id); taking="복용끝"; Log.d("udb screen10","사유: 일수가 지났음");
                    }
                }

                dataList.add(new com.example.myapplication.Log(id,taking,pills,date,warn));
                //Log.d("udb screen10","log아이템 집어넣음");
            }

            db.close();
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

    public void changeTaking(int id){
        //id를 넣으면 해당 row의 복용중 상태를 복용끝으로 바꾼다.
        MyDatabaseOpenHelper helper = new MyDatabaseOpenHelper(this);
        SQLiteDatabase db = helper.getWritableDatabase();
        String sql = "UPDATE prescription SET taking = '복용끝' WHERE _id="+id;
        db.execSQL(sql);


        Log.d("udb screen10","changeTaking 함수, 복용 상태 (taking) 수정");

    }

}
