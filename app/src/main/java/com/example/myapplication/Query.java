package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Query extends AppCompatActivity implements View.OnClickListener{

    private final  String TAG = getClass().getSimpleName();

    // server의 url을 적어준다
    private final String BASE_URL = "http://13.125.80.169:8000/";
    private retroAPI mMyAPI;

    private TextView mListTv;

    private Button mGetButton;
    private Button mPostButton;
    private Button mPatchButton;
    private Button mDeleteButton;
    private Button mQuery;
    private EditText mEdit;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hyowon_test);

        mListTv = findViewById(R.id.result1);

        mGetButton = findViewById(R.id.button1);
        mGetButton.setOnClickListener(this);
        mPostButton = findViewById(R.id.button2);
        mPostButton.setOnClickListener(this);
        mPatchButton = findViewById(R.id.button3);
        mPatchButton.setOnClickListener(this);
        mDeleteButton = findViewById(R.id.button4);
        mDeleteButton.setOnClickListener(this);
        mQuery = findViewById(R.id.button18);
        mQuery.setOnClickListener(this);
        mEdit = findViewById(R.id.edit);

        final String query = mEdit.getText().toString();


        initMyAPI(BASE_URL);
    }

    private void initMyAPI(String baseUrl){

        Log.d(TAG,"initMyAPI : " + baseUrl);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        mMyAPI = retrofit.create(retroAPI.class);
    }


    @Override
    public void onClick(View v) {

        if( v == mQuery){

            final String query = mEdit.getText().toString();
            if(TextUtils.isEmpty(query)){
                mEdit.setError("검색어를 입력해주세요!");
            }else{
                Log.d(TAG,"GET");
                Call<List<Drug>> getCall = mMyAPI.get_posts();
                getCall.enqueue(new Callback<List<Drug>>() {
                    @Override
                    public void onResponse(Call<List<Drug>> call, Response<List<Drug>> response) {
                        if( response.isSuccessful()){
                            Toast.makeText(getApplicationContext(),"연결성공", Toast.LENGTH_SHORT).show();
                            List<Drug> mList = response.body();
                            String result ="0";
                            for( Drug item : mList){
                                String name = item.getDrug_name();
                                if(name.contains(query)){
                                    result += "injection : " + item.getDrug_name() + " company: " + item.getCompany() + "\n\n";
                                }

                            }
                            mListTv.setText(result);
                        }else {
                            Toast.makeText(getApplicationContext(),"연결은 되었으나", Toast.LENGTH_SHORT).show();
                            Log.d(TAG,"Status Code : " + response.code() + response.body());
                        }
                    }

                    @Override
                    public void onFailure(Call<List<Drug>> call, Throwable t) {
                        Toast.makeText(getApplicationContext(),"error2", Toast.LENGTH_SHORT).show();
                        Log.d(TAG,"Fail msg : " + t.getMessage());
                    }

                });

            }

        }else if(v == mPostButton){}
//            Log.d(TAG,"POST");
//
//
//            Drug item = new Drug();
//            item.setTitle("Android title");
//            item.setText("Android text");
//            Call<PostItem> postCall = mMyAPI.post_posts(item);
//            postCall.enqueue(new Callback<PostItem>() {
//                @Override
//                public void onResponse(Call<PostItem> call, Response<PostItem> response) {
//                    if(response.isSuccessful()){
//                        Log.d(TAG,"등록 완료");
//                    }else {
//                        Log.d(TAG,"Status Code : " + response.code());
//                        Log.d(TAG,response.errorBody().toString());
//                        Log.d(TAG,call.request().body().toString());
//                    }
//                }
//
//                @Override
//                public void onFailure(Call<PostItem> call, Throwable t) {
//                    Log.d(TAG,"Fail msg : " + t.getMessage());
//                }
//            });
//        }else if( v == mPatchButton){
//            Log.d(TAG,"PATCH");
//            PostItem item = new PostItem();
//            item.setTitle("android patch title");
//            item.setText("android patch text");
//            //pk 값은 임의로 하드코딩하였지만 동적으로 setting 해서 사용가능
//            Call<PostItem> patchCall = mMyAPI.patch_posts(1,item);
//            patchCall.enqueue(new Callback<PostItem>() {
//                @Override
//                public void onResponse(Call<PostItem> call, Response<PostItem> response) {
//                    if(response.isSuccessful()){
//                        Log.d(TAG,"patch 성공");
//                    }else{
//                        Log.d(TAG,"Status Code : " + response.code());
//                    }
//                }
//
//                @Override
//                public void onFailure(Call<PostItem> call, Throwable t) {
//                    Log.d(TAG,"Fail msg : " + t.getMessage());
//                }
//            });
//
//
//        }else if( v == mDeleteButton){
//            Log.d(TAG,"DELETE");
//            // pk 값은 임의로 변경가능
//            Call<PostItem> deleteCall = mMyAPI.delete_posts(2);
//            deleteCall.enqueue(new Callback<PostItem>() {
//                @Override
//                public void onResponse(Call<PostItem> call, Response<PostItem> response) {
//                    if(response.isSuccessful()){
//                        Log.d(TAG,"삭제 완료");
//                    }else {
//                        Log.d(TAG,"Status Code : " + response.code());
//                    }
//                }
//
//                @Override
//                public void onFailure(Call<PostItem> call, Throwable t) {
//                    Log.d(TAG,"Fail msg : " + t.getMessage());
//                }
//            });
//        }
    }
}




