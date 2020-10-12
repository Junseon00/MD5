package com.example.myapplication;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Adapter_follower extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        //이미지뷰는 나중에★
        TextView name;
        Button bttn;

        MyViewHolder(View view){
            super(view);
            name = view.findViewById(R.id.textView23);
            bttn= view.findViewById(R.id.button19);
            //이미지뷰는 나중에★
        }
    }

    //관리할 자료 리스트!
    private ArrayList<Data_follower> followers;

    Adapter_follower(ArrayList<Data_follower> data_followers){
        this.followers = data_followers;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.follower_item, parent, false);

        return new Adapter_follower.MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {


        Adapter_follower.MyViewHolder myViewHolder = (Adapter_follower.MyViewHolder) holder;

        myViewHolder.name.setText(followers.get(position).getName());

        //클릭이벤트
        Button button = ((MyViewHolder) holder).bttn;

        button.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                android.util.Log.d("udg Adapter_follower","버튼 클릭");
                Intent intent = new Intent(button.getContext(),Missions.class);
                v.getContext().startActivity(intent);
            }
        });
        //이미지뷰는 나중에★
    }
    @Override
    public int getItemCount() {
        return followers.size();
    }
}
