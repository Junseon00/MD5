package com.example.myapplication;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Adapter_followerRequest extends RecyclerView.Adapter< Adapter_followerRequest.RViewHolder> {
    private Context context;

    public static class RViewHolder extends RecyclerView.ViewHolder {
        //이미지뷰는 나중에★
        TextView name,phone;
        Button btn;

        RViewHolder(View view){
            super(view);
            name = view.findViewById(R.id.frname);
            phone=view.findViewById(R.id.frphone);
            btn= view.findViewById(R.id.frbtn);
            //이미지뷰는 나중에★
        }
    }

    //관리할 자료 리스트!
    private ArrayList<Data_friendRequest> followerRequests;

    Adapter_followerRequest(ArrayList<Data_friendRequest> data_followerRequests){
        this.followerRequests = data_followerRequests;
    }


    @Override
    public Adapter_followerRequest.RViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.friend_request_item, parent, false);

        return new Adapter_followerRequest.RViewHolder(v);
    }

    @Override
    public void onBindViewHolder(Adapter_followerRequest.RViewHolder holder, int position) {

        Adapter_followerRequest.RViewHolder myViewHolder = (Adapter_followerRequest.RViewHolder) holder;

        myViewHolder.name.setText(followerRequests.get(position).getName());
        //myViewHolder.phone.setText(followerRequests.get(position).getPhone());
        // 전화번호 보류

        //클릭이벤트
        Button button = ((Adapter_followerRequest.RViewHolder) holder).btn;

        button.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent intent = new Intent(button.getContext(), AddFriendNameSet.class);
                v.getContext().startActivity(intent);
            }
        });
        //이미지뷰는 나중에★
    }
    @Override
    public int getItemCount() {
        return followerRequests.size();
    }

}
