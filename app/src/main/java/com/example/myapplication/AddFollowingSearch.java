package com.example.myapplication;

import android.widget.EditText;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.SearchView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AddFollowingSearch extends AppCompatActivity {
    private final String BASE_URL = "http://13.125.80.169:8000/";
    private retroAPI mMyAPI;

    private SearchView mSearchView;
    private final  String TAG = getClass().getSimpleName();
    private EditText mEdit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_add_friend_search);

        mEdit = findViewById(R.id.edit);

        mSearchView = findViewById(R.id.searchView);
    }

    //retroAPI INIT
    private void initMyAPI(String baseUrl){

        Log.d(TAG,"initMyAPI : " + baseUrl);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        mMyAPI = retrofit.create(retroAPI.class);
    }


    // 검색하기 기능
    public void onClick(View v) {

        final String query = mEdit.getText().toString();
        if (TextUtils.isEmpty(query)) {
            mEdit.setError("찾을 사람의 전화번호나 아이디를 입력해주세요!");
        } else {
            android.util.Log.d(TAG, "GET");
            Call<List<User>> getCall = mMyAPI.get_users();
            getCall.enqueue(new Callback<List<User>>() {
                @Override
                public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                    if (response.isSuccessful()) {
                        Toast.makeText(getApplicationContext(), "연결성공", Toast.LENGTH_SHORT).show();
                        List<User> mList = response.body();
                        String result = "0";
                        for (User item : mList) {
                            String name = item.getPhone();
                            String phone = item.getId();

                            //아이디를 찾았을 때
                            if (name == query) {
                                result += "id : " + item.getId() + " 님. 전화번호 : " + item.getPhone() + "\n\n";

                                //전화번호를 찾았을 때
                            } else if (phone == query) {

                                result += "id : " + item.getId() + " 님. 전화번호 : " + item.getPhone() + "\n\n";
                            }


                        }
                        Toast.makeText(getApplicationContext(), result, Toast.LENGTH_SHORT).show();
                        //mListTv.setText(result);
                    } else {
                        Toast.makeText(getApplicationContext(), "연결은 되었으나", Toast.LENGTH_SHORT).show();
                        android.util.Log.d(TAG, "Status Code : " + response.code() + response.body());
                    }
                }

                @Override
                public void onFailure(Call<List<User>> call, Throwable t) {
                    Toast.makeText(getApplicationContext(), "error2", Toast.LENGTH_SHORT).show();
                    Log.d(TAG, "Fail msg : " + t.getMessage());
                }

            });

        }
    }


}

// 검색 기능 미구현
// https://acholyte.tistory.com/entry/%EC%95%88%EB%93%9C%EB%A1%9C%EC%9D%B4%EB%93%9C-SearchView-%EC%82%AC%EC%9A%A9%ED%95%98%EA%B8%B0