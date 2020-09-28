package com.example.myapplication;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.text.SimpleDateFormat;
import java.util.Date;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;


public class screen7camera extends AppCompatActivity {

    ImageView iv;
    Button send;
    Button takephoto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen7camera);

        //보내기 버튼은일단 비활성화
        send = (Button)findViewById(R.id.button10);
        send.setVisibility(View.INVISIBLE);



    }



    //카메라를 키는 인텐트
    public void camera(View v){
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent,1);

    }


    //카메라 결과
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        iv= (ImageView)findViewById(R.id.iv);
        iv.setImageURI(data.getData());
        //getData로 이미지 URL불러오기.

        //서버로 보낼 수 있게 버튼 활성화
        send =(Button)findViewById(R.id.button10);
        send.setVisibility(View.VISIBLE);

        //서버로 보내는 버튼 click 리스너 설정
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //서버로 전송하는 코드 넣기;
                try{

                    ////날짜, 약 이름, 찍은 이미지
                    String date = returnDate();
                    String name = "default pill name";
                    Bitmap image = (Bitmap)data.getExtras().get("data");


                    //전송 retrofit코드 (& String에 name 변경)

                    //검색 결과 저장 : prescripton 데이터 베이스에
                    MyDatabaseOpenHelper helper = new MyDatabaseOpenHelper(screen7camera.this);
                    SQLiteDatabase db = helper.getWritableDatabase();




                    db.execSQL("insert into searchlog (date, name, image) values(?,?,?)" ,new Object[]{date,name,image}  );
                    db.close();


                }
                catch(Exception e1){
                    Log.d("udb 서버에 send","서버에 사진을 보내는 try- catch문에 에러 발생");
                }

            }
        }
        //------------------------------------------------------------------------------------------



        );

        //사진 찍기는 이제 다시 찍기로 설정
        takephoto =(Button)findViewById(R.id.button9);
        takephoto.setText("사진 다시 찍기");



    }


public String returnDate(){

    // 현재시간을 msec 으로 구한다.
    long now = System.currentTimeMillis();
    // 현재시간을 date2 변수에 저장한다.
    Date date2 = new Date(now);
    // 시간을 나타냇 포맷을 정한다 ( yyyy/MM/dd 같은 형태로 변형 가능 )
    SimpleDateFormat sdfNow = new SimpleDateFormat("yyyy-MM-dd");
    // String 형식으로 format을 변환해 time에 저장.
    String time = sdfNow.format(date2);

    return  time;

}




}