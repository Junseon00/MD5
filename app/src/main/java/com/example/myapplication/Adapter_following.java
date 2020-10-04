package com.example.myapplication;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Adapter_following extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        //이미지뷰는 나중에★
        TextView name;

        MyViewHolder(View view){
            super(view);
            name = view.findViewById(R.id.textView23);
            //이미지뷰는 나중에★
        }
    }
    //관리할 자료 리스트!
    private ArrayList<Data_following> followings;

    Adapter_following(ArrayList<Data_following> data_followings){
        this.followings = data_followings;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.following_item, parent, false);

        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        MyViewHolder myViewHolder = (MyViewHolder) holder;

        myViewHolder.name.setText(followings.get(position).getName());
        //이미지뷰는 나중에★
    }

    @Override
    public int getItemCount() {
        return followings.size();
    }







}
