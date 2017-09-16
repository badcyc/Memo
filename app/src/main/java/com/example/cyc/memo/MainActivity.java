package com.example.cyc.memo;

import android.app.Activity;
import android.content.ContentUris;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.Toast;

import com.example.cyc.memo.date.Date;
import com.example.cyc.memo.date.DateHelper;
import com.example.cyc.memo.date.MyAdapter;
import com.example.cyc.memo.ui.MainFragment;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener
             ,MainFragment.AllMemoCallbacks{

    private Toolbar toolbar;
    private MenuItem menuItem;
    private Button titleButton;
    private FloatingActionButton floatingActionButton;
    private LinearLayout container;
    private RecyclerView recyclerView;
    private int allNumber;
    private int collectNumber;
    private List<Category>categoryList;
    private PopupWindow popupWindow;
    private List<Date>dateList=new ArrayList<>();
   /* private DateHelper dateHelper;
    private SQLiteDatabase database;
    private Cursor cursor;
    private MyAdapter myAdapter;*/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initDate();
        setListner();
    }
    public void initView(){
        toolbar=(Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        floatingActionButton=(FloatingActionButton)findViewById(R.id.floatingActionButton);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        titleButton=(Button)findViewById(R.id.title_btn);
        getFragmentManager().beginTransaction().replace(R.id.container,new MainFragment()).commit();

       /* getDateList();
        recyclerView=(RecyclerView)findViewById(R.id.recycler_view);
        LinearLayoutManager manager=new LinearLayoutManager(this);
        recyclerView.setLayoutManager(manager);
        myAdapter=new MyAdapter(dateList);
        recyclerView.setAdapter(myAdapter);*/

    }
   /* public void getDateList(){
        dateHelper=new DateHelper(this);
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
    }*/
    public void initDate(){
        allNumber=0;
        collectNumber=0;
        categoryList=new ArrayList<>();
        initCategory();
    }
    public void setListner(){
        titleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                View view1=LayoutInflater.from(MainActivity.this).inflate(R.layout.popup_window,null);
                popupWindow=new PopupWindow(view1,
                        LinearLayout.LayoutParams.WRAP_CONTENT,LinearLayout.LayoutParams.WRAP_CONTENT);
                popupWindow.setContentView(view1);
                RecyclerView recyclerViewAll=(RecyclerView)view1.findViewById(R.id.content_menu);
                RecyclerView recyclerViewAdd=(RecyclerView)view1.findViewById(R.id.content_add);
                AllAdapter allAdapter=new AllAdapter(categoryList);
                LinearLayoutManager manager=new LinearLayoutManager(MainActivity.this);

                popupWindow.setBackgroundDrawable(new ColorDrawable(Color.WHITE));
                popupWindow.setOutsideTouchable(true);
                recyclerViewAll.setLayoutManager(manager);
                recyclerViewAll.setAdapter(allAdapter);
                popupWindow.showAsDropDown(titleButton,5,-100);

            }
        });
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this,EditActivity.class);

                startActivity(intent);

            }
        });
    }

    @Override
    public void onListItemCliceked(int position, List<Date> memoList) {
        dateList=memoList;

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (popupWindow != null && popupWindow.isShowing()) {
            popupWindow.dismiss();
            popupWindow = null;
        }
        return super.onTouchEvent(event);
    }

    @Override
    public void onClick(View view) {
        int id=view.getId();
        switch (id){
            case R.id.title_btn:
                break;
            default:
                break;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_list,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id=item.getItemId();
        switch (id){
            case R.id.edit:

                break;
            case R.id.manager:

                break;
        }
        return super.onOptionsItemSelected(item);
    }

    public void initCategory(){
        Category category=new Category("全部备忘录",allNumber);
        Category category1=new Category("我的收藏",collectNumber);
        categoryList.add(category);
        categoryList.add(category1);
    }
}
