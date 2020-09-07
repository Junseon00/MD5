package com.example.myapplication;

import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

public class ViewHolder extends RecyclerView.ViewHolder{

    TextView textView1; //time
    TextView textView2; //memo
    TextView textView3; //yes no


    ViewHolder(View itemView)
    {
        super(itemView);

        textView1 = itemView.findViewById(R.id.rc1);
        textView2 = itemView.findViewById(R.id.rc2);
        textView3 = itemView.findViewById(R.id.rc3);
    }
}