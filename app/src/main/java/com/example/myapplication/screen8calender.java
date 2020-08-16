package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.CalendarView;

import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.concurrent.Executors;

import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.CalendarMode;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener;
import com.prolificinteractive.materialcalendarview.spans.DotSpan;


//


import android.graphics.Color;
import android.graphics.Typeface;
import android.text.style.ForegroundColorSpan;
import android.text.style.RelativeSizeSpan;
import android.text.style.StyleSpan;

import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.DayViewDecorator;
import com.prolificinteractive.materialcalendarview.DayViewFacade;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;

import java.util.Date;

//

public class screen8calender extends AppCompatActivity {

    MaterialCalendarView calendar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen8calender);
        calendar = findViewById(R.id.calendarView);

        //특정 날짜에 점 표시
        String[] result = {"2020,08,18"}; //default date (not used)
        new ApiSimulator(result).executeOnExecutor(Executors.newSingleThreadExecutor());


//        //클릭이벤트
//        calendar.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
//            @Override
//            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
//
//            }
//        });
//
//        int beforeYear = Integer.parseInt(getYear(String.valueOf(befor)))


    }

    private class ApiSimulator extends AsyncTask<Void, Void, List<CalendarDay>> {

        //전달받은 result의 날짜들을 저장함. 사실 쓸모없지만 혹시 모르니 남겨둠.
        String[] Time_Result;

        ApiSimulator(String[] Time_Result){
            this.Time_Result = Time_Result;
        }


        @Override
        protected List<CalendarDay> doInBackground(@NonNull Void... voids) {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            //Calendar calendar = Calendar.getInstance(); //뭐하는 애지?
            ArrayList<CalendarDay> dates = new ArrayList<>(); //특정 날짜를 기억하는 리스트

            //원하는 날짜를 CALENDARday에 추가해준다.//
            

            int month = 7; //달은 원하는 달 -1
            int year = 2020; //년은 그대로
            int dayy = 18; //일
            //calendarday method from = from(int year, int month, int day) parameter
            CalendarDay day = CalendarDay.from(year,month,dayy); //특정날짜의 calendarday instance 생성
            dates.add(day); //CalendarDay instance를 받는다
            //calendar.add(Calendar.DATE, 5); //뭐하는애지?

            dayy=19;
            day = CalendarDay.from(year,month,dayy); //특정날짜의 calendarday instance 생성
            dates.add(day);


            return dates;




        }

        @Override
        protected void onPostExecute(@NonNull List<CalendarDay> calendarDays) {
            super.onPostExecute(calendarDays);

            if (isFinishing()) {
                return;
            }


            calendar.addDecorator(new EventDecorator(Color.RED, calendarDays,screen8calender.this));
            Log.d("udb onpostedExecute실행","onpostesexcute가 실행됨");


        }


    }


}//end of main class