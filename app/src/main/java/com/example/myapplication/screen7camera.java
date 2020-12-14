package com.example.myapplication;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.media.Image;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import org.json.JSONObject;
import com.google.gson.JsonObject;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONException;

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;

import java.util.Date;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class screen7camera extends AppCompatActivity {

    ImageView iv;
    Button send;
    Button takephoto;

    private final String BASE_URL = "http://8923667aefd9.ngrok.io/";
    retroAPI imageApi;

    static int flag = 1;
    int count = 10;
    String thisPill = null;
    String thisNum = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen7camera);

        //보내기 버튼은일단 비활성화
        send = (Button)findViewById(R.id.button10);
        send.setVisibility(View.INVISIBLE);

        initMyAPI(BASE_URL);
    }

    //retrofit 기동
    private void initMyAPI(String baseUrl) {

        //서버에 대한 로그를 더 자세히 얻기 위한 코드
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();

        Log.d("서버센드", "initMyAPI : " + baseUrl);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();

        imageApi = retrofit.create(retroAPI.class);
    }


    //카메라를 키는 인텐트
    public void camera(View v){
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent,1);

    }

    //카메라 결과
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        flag=1;
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
                    Log.d("MYTAG", "지금 1 flag = "+flag);
                    Log.d("udb 서버에 send","try메시 쪽에는 들어옴");

                    ////날짜, 약 이름, 찍은 이미지 포맷 바꾸는 부분
                    String date = returnDate();
                    String name = "default pill name";
                    Bitmap image = (Bitmap)data.getExtras().get("data");
                    String myImg = getStringFromBitmap(image);

                    //Log.d("서버센드", myImg);
                    String ss = getBase64decode(myImg);
                    //Log.d("서버센드", ss);

                    //촬영한 파일을 비트맵 이미지로 바꿔서, 레트로핏에 맞게 처리함
                    File file = savebitmap(image);
                    RequestBody requestFile = RequestBody.create(MediaType.parse("multipart/form-data"), file);
                    MultipartBody.Part body = MultipartBody.Part.createFormData("image", file.getName(), requestFile);

                    //사진에 고유한 아이디 부여 : id1
                    final int id1 = (int)(Math.random()*1000)+1;
                    Log.d("id1 정보", id1+"입니다");

                    //맨처음 flag 1: 이미지를 서버로 보냄
                    if(flag==1){
                        flag=2;
                        Call<ImageType> call1 = imageApi.postImg(id1, body,"hyowon");
                        call1.enqueue(new Callback<ImageType>() {

                            @Override
                            public void onResponse(Call<ImageType> call, Response<ImageType> response) {
                                Log.d("flag1", "진입");
                                if (!response.isSuccessful()) {
                                    android.util.Log.d("flag1", "Status Code : " + response.code());
                                    Toast.makeText(getApplicationContext(), " 실패", Toast.LENGTH_SHORT).show();
                                    return;
                                }

                                //서버에 올려진 이미지 받아오기
                                ImageType posted = response.body();
                                String content = "";
                                content += "Id: " + posted.getId() + "\n";
                                Toast.makeText(getApplicationContext(), content + " 가 업로드되었습니다", Toast.LENGTH_SHORT).show();


                            }

                            @Override
                            public void onFailure(Call<ImageType> call, Throwable t) {

                                Log.d("MYTAG", t.getMessage()+t.getLocalizedMessage());
                                Toast.makeText(getApplicationContext(), "error1 연결은 됐지만..."+t.getMessage(), Toast.LENGTH_SHORT).show();
                            }



                        });//업로드

                        //자동으로 클릭해줌
                        send.performClick();

                        //두번째 flag2 : 고유한 아이디에 따라 prescription 데이터베이스에서 정보를 가져옴
                    }else if(flag==2){
                        flag=3;
                        Call<List<Prescription>> getCall = imageApi.get_pres();
                        getCall.enqueue(new Callback<List<Prescription>>() {
                            @Override
                            public void onResponse(Call<List<Prescription>> call, Response<List<Prescription>> response) {
                                if(response.isSuccessful()){
                                    Toast.makeText(getApplicationContext(),"flag2 진입", Toast.LENGTH_SHORT).show();
                                    List<Prescription> mList = response.body();
                                    String result ="0";
                                    for( Prescription item : mList){
                                        String name = item.getDrug_name();
                                        int idi = item.getIdi();

                                        //idi와 일치할 경우
                                        if(idi == 1){
                                            thisPill = item.getDrug_name();
                                            thisNum = item.getDose_num();
                                            result += "약물 이름 : " + item.getDrug_name() + "/복용 날짜 : " + item.getDose_day() + "\n\n";
                                        }

                                        //약물의 이름을 compare로 보냄ㄴㄴ
                                       compare(thisPill);


                                    }
                                    Log.d("알람으로 쓰기위해",result);
                                    //전송 retrofit코드 (& String에 name 변경)
                                    //검색 결과 저장 : prescripton 데이터 베이스에
                                    //MyDatabaseOpenHelper helper = new MyDatabaseOpenHelper(screen7camera.this);


                                    MyDatabaseOpenHelper helper = new MyDatabaseOpenHelper(getApplicationContext()); //context 뭐 넣어야하지..
                                    SQLiteDatabase db = helper.getWritableDatabase(); //쓰기
                                    int numPerDay = Integer.parseInt(thisNum);
                                    int startTime = 0; // 하루에 먹어야하는 약의 양에 따라, 약을 먹을 시간을 설정(점심기준. 좀 이상하긴하다)
                                    int between = 0;
                                    switch(numPerDay){
                                        case 1 :
                                            startTime = 12; //점심만 먹을 때, 12시를 기준으로 1번.
                                            break;
                                        case 2 :
                                            startTime = 10;
                                            between = 8; //아침, 저녁만 먹을 때. 10시와 18시. 간격은 8
                                            break;
                                        case 3 :
                                            startTime = 10;//아침, 점심, 저녁 세 번 먹을 때. 10시 14시 18시.
                                            between = 4;
                                            break;

                                    }

                                    for(int i=0; i<numPerDay; i++){
                                        db.execSQL("insert into times (hour,minute,memo) values(?,?,?)",new String[]{Integer.toString(startTime),"00",thisPill});
                                        startTime += between;
                                    }
                                    db.close();


                                    //db.execSQL("insert into searchlog (date, name, image) values(?,?,?)" ,new Object[]{date,name,image}  );
                                    //db.close();

                                    send.performClick();
                                }else {
                                    Toast.makeText(getApplicationContext(),"연결은 되었으나", Toast.LENGTH_SHORT).show();
                                    //Log.d("","Status Code : " + response.code() + response.body());
                                }
                            }
                            @Override
                            public void onFailure(Call<List<Prescription>> call, Throwable t) {
                                Toast.makeText(getApplicationContext(),"flag2 error"+t.getMessage(), Toast.LENGTH_SHORT).show();
                                Log.d("333","Fail msg : " + t.getMessage());
                            }
                        });

                    }else if(flag==3){
                        //여기서 병용 중복 방지 코드

                        compare(thisPill);
                        flag=4;




                    }else{
                        //빠져나가기...
                        Log.d("flag",flag+"이거임");
                    }
                }
                catch(Exception e1){
                    Log.d("udb 서버에 send","서버에 사진을 보내는 try- catch문에 에러 발생");
                }
                Log.d("알람으로 쓰기위해","나갑니다");
            }
        });
        //------------------------------------------------------------------------------------------


        //사진 찍기는 이제 다시 찍기로 설정
        takephoto =(Button)findViewById(R.id.button9);
        takephoto.setText("사진 다시 찍기");

        //여기서 병용 금기 약물을 비교하시겠습니까?
        //받은 약 정보를 compare에 넣어서..
        //그걸 서버 병용금기 db에 GET


        //알람으로 등록하시겠습니까? 팝업띄우기(나중에..)


    }


    //이미지 파일 포맷 변환 위한 함수
    private File savebitmap(Bitmap bmp) {
        String extStorageDirectory = Environment.getExternalStorageDirectory().toString();
        OutputStream outStream = null;
        // String temp = null;
        File file = new File(extStorageDirectory, "temp.png");
        if (file.exists()) {
            file.delete();
            file = new File(extStorageDirectory, "temp.png");
        }
        try {
            outStream = new FileOutputStream(file);
            bmp.compress(Bitmap.CompressFormat.PNG, 100, outStream);
            outStream.flush();
            outStream.close();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return file;
    }

    //base64 인코딩
    private String getStringFromBitmap(Bitmap bitmapPicture) {
        final int COMPRESSION_QUALITY = 100;
        String encodedImage;
        ByteArrayOutputStream byteArrayBitmapStream = new ByteArrayOutputStream();
        bitmapPicture.compress(Bitmap.CompressFormat.PNG, COMPRESSION_QUALITY,
                byteArrayBitmapStream);
        byte[] b = byteArrayBitmapStream.toByteArray();
        encodedImage = android.util.Base64.encodeToString(b, android.util.Base64.DEFAULT);
        return encodedImage;
    }

    //base64 디코딩(나중에 지울거임)
    public static String getBase64decode(String content){
        return new String(Base64.decode(content, 0));
    }


    //함수 설명
    //"A,B,C"형태로 약물 목록(A,B,C)을 string형식 파라미터로 전달해주면
    //쪼갠뒤 기존의 복용중인 약품과 둘씩 짝지음
    //지금은 짝지은뒤 아무것도 하지 않고 TOast로 메시지만 보여주지만
    //두개씩 짝지은 녀석들을 서버로 보내 무언가 할 수있을 것.
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
                    //실제상황이면 if  ※!a.equal(b)※ 필요 a!=b여야 비교하는 의미가 있지
                    //이제 a,b를 반복해서 서버로 보내면 된다.
                    Toast.makeText(this, "서버로 보낼 , 새로 인식한 a="+a+"복용중이던 b"+b, Toast.LENGTH_SHORT).show();




                    //서버로 보내기
                    //병용금기로 보내는 부분
                    //일단 데이터베이스가 없는 거같아서..
//                    Call<DB_DrugRelation1> call2 = imageApi.get_drugRel();
//                    call2.enqueue(new Callback<DB_DrugRelation1>() {
//
//                        @Override
//                        public void onResponse(Call<DB_DrugRelation1> call, Response<DB_DrugRelation1> response) {
//                            Log.d("flagCompare", "진입");
//                            if (!response.isSuccessful()) {
//                                android.util.Log.d("flagCompare", "Status Code : " + response.code());
//                                Toast.makeText(getApplicationContext(), " 실패", Toast.LENGTH_SHORT).show();
//                                return;
//                            }
//                            List<DB_DrugRelation1> mList = response.body();
//
//                            for( DB_DrugRelation1 item : mList){
//                                String name = item.getDrug_name();
//                                int idi = item.getIdi();
//                                //idi와 일치할 경우
//                                //여기 밑에서i랑 j랑 비교
//                                if(idi == 1){
//                                    thisPill = item.getDrug_name();
//                                    thisNum = item.getDose_num();
//                                    result += "약물 이름 : " + item.getDrug_name() + "/복용 날짜 : " + item.getDose_day() + "\n\n";
//                                }
//                            }
//                        }
//                        @Override
//                        public void onFailure(Call<DB_DrugRelation1> call, Throwable t) {
//                            Log.d("MYTAG", t.getMessage()+t.getLocalizedMessage());
//                            Toast.makeText(getApplicationContext(), "error1 연결은 됐지만..."+t.getMessage(), Toast.LENGTH_SHORT).show();
//                        }
//                    });//업로드

                }
            }
        }//end of cursor

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


    public void back(View view) {
        Intent intent1 = new Intent(this,screen3.class);
        startActivity(intent1);
    }
}