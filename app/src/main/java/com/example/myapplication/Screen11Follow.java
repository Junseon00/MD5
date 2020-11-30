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

import java.util.ArrayList;

public class Screen11Follow extends AppCompatActivity {
    RecyclerView rv;
    RecyclerView.LayoutManager lm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen11_follow);

        //리사이클러뷰를 일단 등록
        rv = (RecyclerView)findViewById(R.id.rv1);
        rv.setHasFixedSize(true);
        lm=new LinearLayoutManager(this);
        rv.setLayoutManager(lm);

        android.util.Log.d("udb screen11follow","리아시클러뷰 등록. 자료 읽어오기 시작");


        //자료들을 가져와 (혹은 만든다)
        ArrayList<Data_dolvom> datadolvoms = new ArrayList<>(); //넣을 리스트
        try {
            MyDatabaseOpenHelper helper = new MyDatabaseOpenHelper(this);
            SQLiteDatabase db = helper.getWritableDatabase();
            String createSQL = "CREATE TABLE IF NOT EXISTS follow (" + "_id INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "follo INTEGER," +
                    "name TEXT," +
                    "icon INTEGER);";

            db.execSQL(createSQL);
            //임시로 데이터 넣기★
            //db.execSQL("insert into follow (follo,name,icon) values(?,?,?)", new Object[]{0, "아들", 1});
            db.execSQL("insert into follow (follo,name,icon) values(?,?,?)", new Object[]{0, "사위", 1});
            Cursor cursor = db.rawQuery("SELECT _id,name,icon from follow WHERE follo =0;", null);

            //order by _id
            while (cursor.moveToNext()) {
                int _id;
                String name;
                int icon;

                _id = cursor.getInt(0);
                name = cursor.getString(1);
                //icon은 생략

                datadolvoms.add(new Data_dolvom(_id, name));//이미지는 안함★
            }
            db.close();
            android.util.Log.d("udb frag_follower.java","db작업 성공");
        }catch(Exception E){
            Log.d("udb frag_follower","db 작업 실패");}

        //리사이클러뷰 데이터 전달 및 adapter설정
        Adapter_Dolvom adapter = new Adapter_Dolvom(datadolvoms);
        rv.setAdapter(adapter);


    }

    public void toMainScreen(View v){
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
    }

    public void addFriend(View v){
        Intent intent = new Intent(this,AddFollowingSearch.class);
        startActivity(intent);
    }   //추가하기 버튼 누르면 검색창 띄우기
}