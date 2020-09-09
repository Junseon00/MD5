package com.example.myapplication;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Handler;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.Activity;
import android.app.AlertDialog;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;

public class RingtonePlayingService extends Service{

    MediaPlayer mediaPlayer;
    int startId;
    boolean isRunning;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Intent intent = new Intent(this, MainActivity.class);

        //알람울리기
        notif();



    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        //스트링 받아오려면 여기에 삽입

        SharedPreferences pref = getSharedPreferences("sFile", MODE_PRIVATE);
        //key에 저장된 값이 있는지 확인. 아무값도 들어있지 않으면 ""를 반환
        String text = pref.getString("alarm","");

        assert text != null;
        switch (text) {
            case "on":
                startId = 1;
                break;
            case "off":
                startId = 0;
                break;
            default:
                startId = 0;
                break;
        }

        // 알람음 재생 X , 알람음 시작 클릭
        if(!this.isRunning && startId == 1) {

            mediaPlayer = MediaPlayer.create(this,R.raw.ringtone);
            mediaPlayer.start();
            Log.d("mediaplayeron","on");
            this.isRunning = true;
            this.startId = 0;



            long millis = System.currentTimeMillis();

            // 현재시간을
            Date now = new Date(millis);
            SimpleDateFormat nowH = new SimpleDateFormat("H");
            String hour = nowH.format(now);

            //현재 분
            SimpleDateFormat nowM = new SimpleDateFormat("m");
            String minute = nowM.format(now);
            int min = Integer.parseInt(minute) +1;
            minute = ""+min;

            Log.d("udb RingTonPlaySer","알람이 울린 시각 = "+hour+":"+minute);

            //팝업
            didyoueat(hour,minute);

        }




        // 알람음 재생 O , 알람음 종료 버튼 클릭
        else if(this.isRunning && startId == 0) {

            mediaPlayer.stop();
            mediaPlayer.reset();
            mediaPlayer.release();

            this.isRunning = false;
            this.startId = 0;
        }

        // 알람음 재생 X , 알람음 종료 버튼 클릭
        else if(!this.isRunning && startId == 0) {

            this.isRunning = false;
            this.startId = 0;

        }

        // 알람음 재생 O , 알람음 시작 버튼 클릭
        else if(this.isRunning && startId == 1){

            this.isRunning = true;
            this.startId = 1;

        }

        else {
        }
        return START_NOT_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        Log.d("onDestory() 실행", "서비스 파괴");

    }

    //약을 먹었는지 체크하기 전애 20초의 시간을 준다
    public void didyoueat(String hour,String minute){

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            //기다린 후 팝업을 띄운다.
            @RequiresApi(api = Build.VERSION_CODES.O)
            public void run() {
                //팝업 액티비티 호출
                Intent intent = new Intent(RingtonePlayingService.this, PopupActivity.class);
                intent.putExtra("hour",hour);
                intent.putExtra("minute",minute);
                startActivity(intent);
                Log.d("popup","20초 후 popup 실행");
                //((RingtonePlayingService) mContext).startActivityForResult(intent, 1);

//                @Override
//                protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//                    if(requestCode==1){
//                        if(resultCode==RESULT_OK){
//                            //데이터 받기
//                            String result = data.getStringExtra("result");
//                            txtResult.setText(result);
//                        }
//                    }
//                }//END OF ONactivity result



            }
        }, 20000);  // 20초
    }

//헤드업 노티피 케이션. 보이기 쉽게 표시
    public void notif(){
        if (Build.VERSION.SDK_INT >= 26) {
            String CHANNEL_ID = "default";
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID,
                    "Channel human readable title",
                    NotificationManager.IMPORTANCE_HIGH);

            ((NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE)).createNotificationChannel(channel);

            Notification notification = new NotificationCompat.Builder(this, CHANNEL_ID)
                    .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                    .setContentTitle("의약품 복용 시간 안내")
                    .setContentText("의약품을 복용하실 시간입니다.")
                    .setSmallIcon(R.mipmap.ic_launcher)

                    .build();

            startForeground(1, notification);
        }
    }

}