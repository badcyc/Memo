package com.example.cyc.memo.ui;

import android.app.Fragment;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import com.example.cyc.memo.EditActivity;
import com.example.cyc.memo.R;
import com.example.cyc.memo.date.Date;
import com.example.cyc.memo.date.DateHelper;
import com.example.cyc.memo.date.MyAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by cyc on 17-9-16.
 */

public class MainFragment extends Fragment {


    private RecyclerView recyclerView;
    private List<Date> dateList=new ArrayList<>();
    private DateHelper dateHelper;
    private SQLiteDatabase database;
    private Cursor cursor;
    private MyAdapter myAdapter;
    private AllMemoCallbacks allMemoCallbacks;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getDateList();

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_list,container,false);
        recyclerView=(RecyclerView)view.findViewById(R.id.recycler_view);
        LinearLayoutManager manager=new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(manager);
        myAdapter=new MyAdapter(dateList);
        recyclerView.setAdapter(myAdapter);
        return view;
    }
 /*   public class AllListListener implements AdapterView.OnItemClickListener{
        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
            if(dateList!=null){
                sendData(i,dateList);
                Date date=dateList.get(i);
                Intent intent=new Intent(getActivity(), EditActivity.class);
                intent.putExtra("title",date.getTitle());
                intent.putExtra("content",date.getContent());
                getActivity().startActivity(intent);
            }
        }
    }*/
    private void sendData(int position,List<Date> musicInfoList){
        if (allMemoCallbacks != null){
            allMemoCallbacks.onListItemCliceked(position,musicInfoList);
        }
    }
    public static interface AllMemoCallbacks{

        void onListItemCliceked(int position,List<Date> memoList);
    }
    public void getDateList(){
        dateHelper=new DateHelper(getActivity());
        database = dateHelper.getWritableDatabase();
        cursor=database.query("memo_data",null,null,null,null,null,null,null);
        if(cursor!=null){
            int num=cursor.getCount();
            for(int i=0;i<num;i++) {
                cursor.moveToNext();
                String title=cursor.getString(cursor.getColumnIndex("title"));
                String content=cursor.getString(cursor.getColumnIndex("content"));
                String clockTime=cursor.getString(cursor.getColumnIndex("clock_time"));
                String editLast=cursor.getString(cursor.getColumnIndex(Date.EDIT_LAST));
                String createTime= cursor.getString(cursor.getColumnIndex(Date.CREATE_TIME));
                boolean clockIs=Boolean.valueOf(cursor.getString(cursor.getColumnIndex(Date.CLOCK_IS)));
                String musicName=cursor.getString(cursor.getColumnIndex(Date.MUSIC_NAME));
                Date date=new Date(createTime,title,content,clockTime,editLast,clockIs,musicName);
                dateList.add(date);
            }
        }
        cursor.close();
        database.close();
    }
}
