package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

public class Frag_dolvom extends Fragment {
    RecyclerView rv;
    RecyclerView.LayoutManager lm;

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    public Frag_dolvom() {}

    public static Frag_dolvom newInstance(String param1, String param2) {
        Frag_dolvom fragment = new Frag_dolvom();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_frag_dolvom, container, false);
        //리사이클러뷰 등록
        rv = (RecyclerView)v.findViewById(R.id.rv1);
        rv.setHasFixedSize(true);
        lm=new LinearLayoutManager(getActivity());
        rv.setLayoutManager(lm);

        //자료들 가져오기
        ArrayList<Data_friendRequest> datarequests = new ArrayList<>(); //넣을 리스트

        android.util.Log.d("udb screen11follow","리아시클러뷰 등록. 자료 읽어오기 시작");


        //자료들을 가져와 (혹은 만든다)
        ArrayList<Data_dolvom> datadolvoms = new ArrayList<>(); //넣을 리스트

        try {
            MyDatabaseOpenHelper helper = new MyDatabaseOpenHelper(getActivity());
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

        return v;
    }
}