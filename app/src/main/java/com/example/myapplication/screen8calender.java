package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
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
import android.widget.Toast;

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


        //클릭이벤트
        Intent intent = new Intent(this, AlarmCalenderPopUp.class);
        calendar.setOnDateChangedListener(new OnDateSelectedListener() {
            @Override
            public void onDateSelected(@NonNull MaterialCalendarView widget, @NonNull CalendarDay date, boolean selected) {

                int Year = date.getYear();
                int Month = date.getMonth() + 1;
                int Day = date.getDay();

                Log.i("Year test", Year + "");
                Log.i("Month test", Month + "");
                Log.i("Day test", Day + "");

                String m;
                String d;

                //yyyy/mm/dd꼴로 만들어주기
                if(Month<10){
                    m="0"+Month;
                }
                else {m=""+Month;}

                if(Day <10){
                    d = "0"+Day;
                }
                else{d=""+Day;}

                String shot_Day = Year + "/" + m + "/" + d;

                Log.i("shot_Day test", shot_Day + "");
                calendar.clearSelection();

                //Toast.makeText(getApplicationContext(), shot_Day+"선택" , Toast.LENGTH_SHORT).show();

                //calendar popu intent부르기
                intent.putExtra("date", shot_Day);
                startActivityForResult(intent, 1);
            }




        });



    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode==1){
            if(resultCode==RESULT_OK){
                //캘린더 popup부른 뒤
                // ..아무것도 안해
            }
        }
    }


    public void toscM(View v){
        Intent intent1 = new Intent(this,MainActivity.class);
        startActivity(intent1);
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

            try{
            //원하는 날짜를 CALENDARday에 추가해준다.//
            //db에서 모든 시간 다 불러와 DB에 설정 //알람이 울렸던 날짜를 가져온다.
            MyDatabaseOpenHelper helper = new MyDatabaseOpenHelper(screen8calender.this);
            SQLiteDatabase db = helper.getWritableDatabase(); //wrtie이네?!
            Cursor cursor = db.rawQuery("SELECT DISTINCT date from patient order by _id",null);

            while(cursor.moveToNext()){
                String day;
                day=cursor.getString(0); //day format은 "yyyy/MM/dd"임
                String[] time = day.split("/"); // '/'기준으로 string을 자른다.
                int year = Integer.parseInt(time[0]); //yyyy
                int month = Integer.parseInt(time[1]) -1; //mm
                int dd = Integer.parseInt(time[2]); //dd
                CalendarDay cd = CalendarDay.from(year,month,dd);
                dates.add(cd);

            }

            return dates;}catch(Exception e){return null;}


        }

        @Override
        protected void onPostExecute(@NonNull List<CalendarDay> calendarDays) {
            super.onPostExecute(calendarDays);

            if (isFinishing()) {
                return;
            }

            if (calendarDays != null){
            calendar.addDecorator(new EventDecorator(Color.RED, calendarDays,screen8calender.this));
            Log.d("udb onpostedExecute실행","onpostesexcuted의 decotrator가 실행됨");}
            else {return;}



        }


    }


}//end of main class