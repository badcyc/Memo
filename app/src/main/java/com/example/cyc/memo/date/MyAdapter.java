package com.example.cyc.memo.date;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cyc.memo.EditActivity;
import com.example.cyc.memo.R;

import java.util.List;

/**
 * Created by cyc on 17-9-16.
 */

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
    private List<Date> dateList;

    static class ViewHolder extends RecyclerView.ViewHolder{
        View dateView;
        private TextView mainTitleTv;
        private TextView subTitleTv;
        private ImageView iconTitleIv;
        public ViewHolder(View view){
            super(view);
           dateView=view;
            mainTitleTv=(TextView)view.findViewById(R.id.main_title);
            subTitleTv=(TextView)view.findViewById(R.id.sub_title);
            iconTitleIv=(ImageView)view.findViewById(R.id.icon_title);
        }

    } public MyAdapter(List<Date> dateList){
        this.dateList=dateList;
        }

    @Override
    public ViewHolder onCreateViewHolder(final ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item,parent,false);
        final ViewHolder viewHolder=new ViewHolder(view);
        viewHolder.dateView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int position=viewHolder.getAdapterPosition();
                Date date=dateList.get(position);
                Intent intent=new Intent(parent.getContext(), EditActivity.class);
                intent.putExtra("title",date.getTitle());
                intent.putExtra("content",date.getContent());
                parent.getContext().startActivity(intent);
                Toast.makeText(view.getContext(),"fsadf"+date.getTitle(),Toast.LENGTH_SHORT).show();
            }
        });
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Date date=dateList.get(position);
        holder.mainTitleTv.setText(date.getTitle());
        holder.subTitleTv.setText(date.getContent());
    }

    @Override
    public int getItemCount() {
        return dateList.size();
    }
}
