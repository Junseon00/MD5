package com.example.myapplication;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;
import android.widget.SearchView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

public class AddFollowingSearch extends AppCompatActivity {
    private SearchView mSearchView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_add_following_search);

        mSearchView = findViewById(R.id.searchView);
    }
}

// 검색 기능 미구현
// https://acholyte.tistory.com/entry/%EC%95%88%EB%93%9C%EB%A1%9C%EC%9D%B4%EB%93%9C-SearchView-%EC%82%AC%EC%9A%A9%ED%95%98%EA%B8%B0