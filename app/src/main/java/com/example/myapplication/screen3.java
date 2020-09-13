package com.example.myapplication;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;

public class screen3 extends AppCompatActivity {

    private Button bt1, bt2, menu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen3);

        menu = findViewById(R.id.menu);

        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                MenuFragment fragment1 = new MenuFragment();
                transaction.replace(R.id.frame, fragment1);
                transaction.commit();
            }
        });




        bt1 = findViewById(R.id.btn1);
        bt2 = findViewById(R.id.btn2);

//        bt1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view){
//                Intent intent = new Intent(X.this, X.class);
//                startActivity(intent);
//            }
//        });

//          bt2.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view){
//                Intent intent = new Intent(X.this, X.class);
//                startActivity(intent);
//            }
//        });



    }

    public void toCamera(View v){
        Intent intent = new Intent(this,screen7camera.class );
        startActivity(intent);

    }//to the camera activity

    public void toQuery(View v){
        Intent intent = new Intent(this,Query.class );
        startActivity(intent);

    }//to the query activity


}
