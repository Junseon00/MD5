package com.example.myapplication;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Adapter_Dolvom extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        //이미지뷰는 나중에★
        TextView name;
        Button bttn1; //활동 내역 보기
        Button bttn2;//미션 목록
        Button bttn3; //미션 내기

        MyViewHolder(View view){
            super(view);
            name = view.findViewById(R.id.textView23);
            bttn1= view.findViewById(R.id.button19);
            bttn2 = view.findViewById(R.id.button20);
            bttn3 = view.findViewById(R.id.button21);
            //이미지뷰는 나중에★
        }
    }

    //관리할 자료 리스트!
    private ArrayList<Data_dolvom> dolvoms;
    //자료 리스트 초기화
    Adapter_Dolvom(ArrayList<Data_dolvom> data_dolvoms){
        this.dolvoms = data_dolvoms;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.dolvom_item, parent, false);

        return new Adapter_Dolvom.MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {


        Adapter_Dolvom.MyViewHolder myViewHolder = (Adapter_Dolvom.MyViewHolder) holder;

        myViewHolder.name.setText(dolvoms.get(position).getName());

        //클릭이벤트
        Button button1 = ((MyViewHolder) holder).bttn1; //활동 내역
        Button button2 = ((MyViewHolder) holder).bttn2; // 미션 목록
        Button button3 = ((MyViewHolder) holder).bttn3; //미션 내기

        button2.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                android.util.Log.d("udg adapter_dolvom","버튼 클릭");
                Intent intent = new Intent(button3.getContext(),Missions.class);
                v.getContext().startActivity(intent);
            }
        });

        button3.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Log.d("udg Adapter_Dolvom","버튼 클릭");

                //데이터 담아서 팝업(액티비티) 호출
                Intent intent = new Intent(v.getContext(), mission_popup.class);
                //미션 보낼 사람
                String to = dolvoms.get(position).getName();
                intent.putExtra("to", to);
                v.getContext().startActivity(intent);
            }
        });

        //이미지뷰는 나중에★
    }
    @Override
    public int getItemCount() {
        return dolvoms.size();
    }
}
