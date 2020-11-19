package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Toast;

public class Test extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
    }



    //버튼
    public void mOnPopupClick(View v){
        //데이터 담아서 팝업(액티비티) 호출
        Intent intent = new Intent(this, mission_popup.class);
        intent.putExtra("data", "Test Popup");
        startActivityForResult(intent, 1);



    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode==1){
            if(resultCode==RESULT_OK){
                //데이터 받기
                String result = data.getStringExtra("result");

            }
        }
    }

    public void activate1(View v) {
        compare("a,b,c");


    }

    //테스트할 함수--------------------------------------------
    public void compare(String medicstring){
        String[] mediclist = medicstring.split(","); //"a,b,c"를 ["a","b","c"]로 쪼갠다

        //db에서 모든 복용죽 약품 불러옴. prescription table은 약품 검색 기록 저장
        MyDatabaseOpenHelper helper = new MyDatabaseOpenHelper(this);
        SQLiteDatabase db = helper.getWritableDatabase();
        Cursor cursor = db.rawQuery("select pills  from prescription WHERE taking = '복용중' order by _id ",null); //검색 내역도 "d,e,f"형식으로 저장되어있다

        while(cursor.moveToNext()) {
            String[] takinglist = cursor.getString(0).split(","); //"d,e,f" -> ["d","e","f"]
            for (int i = 0; i < mediclist.length; i++) {
                String a = mediclist[i]; //비교대상, 새로 인식한 "A"
                for (int j = 0; j<takinglist.length ; j++){
                    String b = takinglist[j];

                    new Handler().postDelayed(new Runnable()
                    {

                        public void run()
                        {
                            //여기에 딜레이 후 시작할 작업들을 입력
                            Toast.makeText(Test.this, "서버로 보낼 , 새로 인식한 a="+a+"복용중이던 b"+b, Toast.LENGTH_SHORT).show();
                        }
                    }, 500);// 0.5초 정도 딜레이를 준 후 시작

                }
            }
        }//end of cursor

    }
    //--------------------------------------------------------------------------------



}