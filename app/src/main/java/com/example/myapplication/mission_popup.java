package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager.widget.ViewPager;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.google.android.material.tabs.TabLayout;

import java.util.Objects;

public class mission_popup extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //타이틀바 없애기
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mission_popup);
        Objects.requireNonNull(getWindow()).setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        // dialog 배경 투명하게


        // 라디오그룹 참조
        RadioGroup radioGroup = (RadioGroup)findViewById(R.id.radioGroup);

        // 클릭이벤트
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int id) {
                RadioButton select = (RadioButton)findViewById(id);

                if (select.getText().equals("직접 작성하기")){
                    Intent intent = new Intent(mission_popup.this, Mission1.class);
                    intent.putExtra("data", "Test Popup");
                    startActivity(intent);
                    finish();}
                if( select.getText().equals("자동 문구에서 선택하기")){
                    Intent intent = new Intent(mission_popup.this, Mission2.class);
                    intent.putExtra("data", "Test Popup");
                    startActivity(intent);
                    finish();
                }

            }
        });



    }



}