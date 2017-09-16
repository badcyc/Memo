package com.example.cyc.memo;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by cyc on 17-9-14.
 */

public class AllAdapter extends RecyclerView.Adapter<AllAdapter.ViewHolder> {

    private List<Category> mList;
    static class ViewHolder extends RecyclerView.ViewHolder{
        TextView textView_content;
        TextView textView_num;
        View categoryView;
        public ViewHolder(View view){
            super(view);
            categoryView=view;
            textView_content=(TextView)view.findViewById(R.id.textView_content);
            textView_num=(TextView)view.findViewById(R.id.textView_num);

        }
    }
    public AllAdapter(List<Category> list){
        mList=list;

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.menu_item,parent,false);
        ViewHolder viewHolder=new ViewHolder(view);
        viewHolder.categoryView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Category category=mList.get(position);
        holder.textView_content.setText(category.getTitle());
        holder.textView_num.setText(String.valueOf(category.getNumber()));

    }

    @Override
    public int getItemCount() {
        return mList.size();
    }
}


