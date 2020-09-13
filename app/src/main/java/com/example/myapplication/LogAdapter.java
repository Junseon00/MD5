package com.example.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class LogAdapter extends RecyclerView.Adapter<LogAdapter.ViewHolder>{

    //log 데이터 인스턴스를 담을 리스트
    private ArrayList<Log> myDataList = null;

    //생성자
    public LogAdapter(ArrayList<Log> dataList)
    {
        myDataList = dataList;
    }

    // 아이템 뷰를 저장하는 뷰홀더 클래스.
    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView id;
        TextView date;
        TextView pills;
        TextView taking;
        TextView warning;

        ViewHolder(View itemView) {
            super(itemView);

            // 뷰 객체에 대한 참조. (hold strong reference)
            id = itemView.findViewById(R.id.textView17);
            date = itemView.findViewById(R.id.textView19);
            pills = itemView.findViewById(R.id.textView18);
            taking = itemView.findViewById(R.id.textView20);
            warning = itemView.findViewById(R.id.textView21);
        }
    }




    @Override
    public LogAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        Context context = parent.getContext();
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        //전개자(Inflater)를 통해 얻은 참조 객체를 통해 뷰홀더 객체 생성
        View view = inflater.inflate(R.layout.logitem, parent, false);
        LogAdapter.ViewHolder viewHolder = new LogAdapter.ViewHolder(view);

        return viewHolder;
    }

    @Override
    public int getItemCount()
    {
        //Adapter가 관리하는 전체 데이터 개수 반환
            return myDataList.size();
    }

    @Override
    public void onBindViewHolder(LogAdapter.ViewHolder viewHolder, int position)
    {
        //ViewHolder가 관리하는 View에 position에 해당하는 데이터 바인드
        // 1= time //2=memo //3=yes or no

        int id = myDataList.get(position).getId();
        android.util.Log.d("udb logadapter","onbind view holder start, id="+id);
        viewHolder.id.setText(""+id);
        viewHolder.date.setText(myDataList.get(position).getdate());
        viewHolder.pills.setText(myDataList.get(position).getPills());
        viewHolder.taking.setText(myDataList.get(position).gettaking());
        viewHolder.warning.setText(myDataList.get(position).getwarning());

        android.util.Log.d("udb logadapter","onbind view holder end");

    }





}
