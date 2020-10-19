package com.example.myapplication;

import android.content.Intent;
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

    private final String BASE_URL = "http://016b5cea4870.ngrok.io";
    retroAPI imageApi;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen7camera);

        //보내기 버튼은일단 비활성화
        send = (Button)findViewById(R.id.button10);
        send.setVisibility(View.INVISIBLE);



        initMyAPI(BASE_URL);

    }

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
                    Log.d("udb 서버에 send","try메시 쪽에는 들어옴");
                    ////날짜, 약 이름, 찍은 이미지
                    String date = returnDate();
                    String name = "default pill name";
                    Bitmap image = (Bitmap)data.getExtras().get("data");
                    String myImg = getStringFromBitmap(image);

                    Log.d("서버센드", myImg);
                    String ss = getBase64decode(myImg);
                    //Log.d("서버센드", ss);



                    //File file = new File(String.valueOf(data.getData()));
                    File file = savebitmap(image);
                    RequestBody requestFile = RequestBody.create(MediaType.parse("multipart/form-data"), file);
                    MultipartBody.Part body = MultipartBody.Part.createFormData("image", file.getName(), requestFile);

                    //json object~
                    JSONObject Imaged = new JSONObject();

                    try{
                        Imaged.put("image",myImg);

                    } catch (JSONException e1) {
                        e1.printStackTrace();
                    }


                    //RequestBody userID = RequestBody.create(MediaType.parse("multipart/form-data"), "1243");
                    final int id = 777;

                    //ImageType posting = new ImageType(id, Imaged);
                    Call<ImageType> call1 = imageApi.postImg(id, body);
                    call1.enqueue(new Callback<ImageType>() {
                        @Override
                        public void onResponse(Call<ImageType> call, Response<ImageType> response) {
                            if (!response.isSuccessful()) {
                                android.util.Log.d("서버센드", "Status Code : " + response.code());
                                Toast.makeText(getApplicationContext(), " 실패", Toast.LENGTH_SHORT).show();
                                return;
                            }
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

                        //전송 retrofit코드 (& String에 name 변경)

                        //검색 결과 저장 : prescripton 데이터 베이스에
                        //MyDatabaseOpenHelper helper = new MyDatabaseOpenHelper(screen7camera.this);
                        //SQLiteDatabase db = helper.getWritableDatabase();


                        //db.execSQL("insert into searchlog (date, name, image) values(?,?,?)" ,new Object[]{date,name,image}  );
                        //db.close();
                    });

                    Log.d("알람으로 쓰기위해...","GET");
                    Call<List<Prescription>> getCall = imageApi.get_pres();
                    getCall.enqueue(new Callback<List<Prescription>>() {
                        @Override
                        public void onResponse(Call<List<Prescription>> call, Response<List<Prescription>> response) {
                            if( response.isSuccessful()){
                                Toast.makeText(getApplicationContext(),"연결성공", Toast.LENGTH_SHORT).show();
                                List<Prescription> mList = response.body();
                                String result ="0";
                                for( Prescription item : mList){
                                    String name = item.getDrug_name();
                                    int id = item.getIdi();
                                    if(id == 1){
                                        result += "약물 이름 : " + item.getDrug_name() + "/복용 날짜 : " + item.getDose_day() + "\n\n";
                                    }

                                }

                                Log.d("알람으로 쓰기위해",result + response.body());


                            }else {
                                Toast.makeText(getApplicationContext(),"연결은 되었으나", Toast.LENGTH_SHORT).show();
                                //Log.d("","Status Code : " + response.code() + response.body());
                            }
                        }

                        @Override
                        public void onFailure(Call<List<Prescription>> call, Throwable t) {
                            Toast.makeText(getApplicationContext(),"error2", Toast.LENGTH_SHORT).show();
                            //Log.d(TAG,"Fail msg : " + t.getMessage());
                        }

                    });







                }
                catch(Exception e1){
                    Log.d("udb 서버에 send","서버에 사진을 보내는 try- catch문에 에러 발생");
                }

            }
        });
        //------------------------------------------------------------------------------------------



        //사진 찍기는 이제 다시 찍기로 설정
        takephoto =(Button)findViewById(R.id.button9);
        takephoto.setText("사진 다시 찍기");



    }

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