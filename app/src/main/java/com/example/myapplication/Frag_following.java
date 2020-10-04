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

import org.opencv.core.Rect;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Frag_following#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Frag_following extends Fragment {

    RecyclerView rv;
    RecyclerView.LayoutManager lm;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Frag_following() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Frag_following.
     */
    // TODO: Rename and change types and number of parameters
    public static Frag_following newInstance(String param1, String param2) {
        Frag_following fragment = new Frag_following();
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
        View v = inflater.inflate(R.layout.fragment_frag_following, container, false);

        //리사이클러뷰를 일단 등록
        rv = (RecyclerView)v.findViewById(R.id.follow);
        rv.setHasFixedSize(true);
        lm=new LinearLayoutManager(getActivity());
        rv.setLayoutManager(lm);

        Log.d("udb frag_following","리아시클러뷰 등록. 자료 읽어오기 시작");


        //자료들을 가져와 (혹은 만든다)
        ArrayList<Data_following> datafollowing = new ArrayList<>(); //넣을 리스트
        try {
            MyDatabaseOpenHelper helper = new MyDatabaseOpenHelper(getActivity());
            SQLiteDatabase db = helper.getWritableDatabase();
            String createSQL = "CREATE TABLE IF NOT EXISTS follow (" + "_id INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "follo INTEGER," +
                    "name TEXT," +
                    "icon INTEGER);";

            db.execSQL(createSQL);
            //임시로 데이터 넣기★
            //db.execSQL("insert into follow (follo,name,icon) values(?,?,?)", new Object[]{1, "할머니", 1});
            Cursor cursor = db.rawQuery("SELECT _id,name,icon from follow WHERE follo =1;", null);

            //order by _id
            while (cursor.moveToNext()) {
                int _id;
                String name;
                int icon;

                _id = cursor.getInt(0);
                name = cursor.getString(1);
                //icon은 생략

                datafollowing.add(new Data_following(_id, name));//이미지는 안함
            }
            db.close();
            Log.d("udb frag_following.java","db작업 성공");
        }catch(Exception E){Log.d("udb frag_following","db 작업 실패");}

        //리사이클러뷰 데이터 전달 및 adapter설정
        Adapter_following adapter = new Adapter_following(datafollowing);
        rv.setAdapter(adapter);




        return v;
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_frag_following, container, false);

    }


}