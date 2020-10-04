package com.example.myapplication;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Frag_follower#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Frag_follower extends Fragment {
    RecyclerView rv;
    RecyclerView.LayoutManager lm;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Frag_follower() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Frag_follower.
     */
    // TODO: Rename and change types and number of parameters
    public static Frag_follower newInstance(String param1, String param2) {
        Frag_follower fragment = new Frag_follower();
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
        View v = inflater.inflate(R.layout.fragment_frag_follower, container, false);
        //리사이클러뷰를 일단 등록
        rv = (RecyclerView)v.findViewById(R.id.follower);
        rv.setHasFixedSize(true);
        lm=new LinearLayoutManager(getActivity());
        rv.setLayoutManager(lm);

        android.util.Log.d("udb frag_follower","리아시클러뷰 등록. 자료 읽어오기 시작");


        //자료들을 가져와 (혹은 만든다)
        ArrayList<Data_follower> datafollower = new ArrayList<>(); //넣을 리스트
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
            //db.execSQL("insert into follow (follo,name,icon) values(?,?,?)", new Object[]{0, "사위", 1});
            Cursor cursor = db.rawQuery("SELECT _id,name,icon from follow WHERE follo =0;", null);

            //order by _id
            while (cursor.moveToNext()) {
                int _id;
                String name;
                int icon;

                _id = cursor.getInt(0);
                name = cursor.getString(1);
                //icon은 생략

                datafollower.add(new Data_follower(_id, name));//이미지는 안함★
            }
            db.close();
            android.util.Log.d("udb frag_follower.java","db작업 성공");
        }catch(Exception E){
            Log.d("udb frag_follower","db 작업 실패");}

        //리사이클러뷰 데이터 전달 및 adapter설정
        Adapter_follower adapter = new Adapter_follower(datafollower);
        rv.setAdapter(adapter);




        return v;
        // Inflate the layout for this fragment
       // return inflater.inflate(R.layout.fragment_frag_follower, container, false);
    }
}