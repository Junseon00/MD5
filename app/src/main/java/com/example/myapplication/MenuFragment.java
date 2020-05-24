package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;

public class MenuFragment extends Fragment {

    private View view;

    public MenuFragment() {

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.activity_menu_fragment, container, false);

        Button bt1 = view.findViewById(R.id.myP);
        Button bt2 = view.findViewById(R.id.main);

        bt1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            }
        });





        return inflater.inflate(R.layout.activity_menu_fragment, container, false);


    }

}
