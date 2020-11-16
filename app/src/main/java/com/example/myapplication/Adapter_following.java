package com.example.myapplication;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import static android.app.Activity.RESULT_OK;

public class Adapter_following extends RecyclerView.Adapter<Adapter_following.ViewHolder> {

    //관리할 자료 리스트!
    private ArrayList<Data_following> followings;


    // 아이템 뷰를 저장하는 뷰홀더 클래스.
    public class ViewHolder extends RecyclerView.ViewHolder {
        //이미지뷰는 나중에★
        TextView name;
        Button button;

        ViewHolder(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.textView23);
            button = itemView.findViewById(R.id.button21);
            //이미지뷰는 나중에★

        }


    }

    //데이터 대입
    Adapter_following(ArrayList<Data_following> data_followings) {
        this.followings = data_followings;
    }


    @Override
    public Adapter_following.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext() ;
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) ;

        View view = inflater.inflate(R.layout.following_item, parent, false) ;
        Adapter_following.ViewHolder vh = new Adapter_following.ViewHolder(view) ;

        return vh ;
    }

    // onBindViewHolder() - position에 해당하는 데이터를 뷰홀더의 아이템뷰에 표시.
    @Override
    public void onBindViewHolder(Adapter_following.ViewHolder holder, int position) {
        ViewHolder myViewHolder = (ViewHolder) holder;
        myViewHolder.name.setText(followings.get(position).getName());
        //이미지뷰는 나중에★
        Button button=holder.button;

        button.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Log.d("udg SimpleTextAdapter","버튼 클릭");

                //데이터 담아서 팝업(액티비티) 호출
                Intent intent = new Intent(v.getContext(), mission_popup.class);
                //미션 보낼 사람
                String to = followings.get(position).getName();
                intent.putExtra("to", to);
                v.getContext().startActivity(intent);
            }
        });


    }

    // getItemCount() - 전체 데이터 갯수 리턴.
    @Override
    public int getItemCount() {
        return followings.size();
    }






}

