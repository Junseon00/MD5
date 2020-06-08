package com.example.myapplication;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

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
        iv.setImageURI(data.getData()); //getData로 이미지 불러오기.

        //서버로 보낼 수 있게
        send =(Button)findViewById(R.id.button10);
        send.setVisibility(View.VISIBLE);

        //서버로 보내는 버튼 click 리스너 설정
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //서버로 전송하는 코드 넣기;
            }
        });

        //사진 찍기는 이제 다시 찍기로 설정
        takephoto =(Button)findViewById(R.id.button9);
        takephoto.setText("사진 다시 찍기");



    }






}