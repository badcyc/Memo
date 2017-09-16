package com.example.cyc.memo;

import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.PixelFormat;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.cyc.memo.date.Date;
import com.example.cyc.memo.date.DateHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by cyc on 17-9-14.
 */

public class EditActivity extends AppCompatActivity implements View.OnClickListener {
        ///MyScrollView.OnScrollListener{

    private Button categoryButton;
    private Button saveButton;

    private EditText titleEdit;
    private EditText contentEdit;

    private MyScrollView myScrollView;
    private StringBuffer date=new StringBuffer();
    private StringBuffer time=new StringBuffer();
    private int year,mouth,day,hour,minute;
    private DateHelper dateHelper;
    private String titleText;
    private String contentText;
    private boolean isClock;
    private String clockTime;
    private String musicName=null;
    private String listTimeEdit;
    private SQLiteDatabase database=null;
    private Cursor cursor;
    private List list=new ArrayList();
 //   private int tabHeight,tabTop,scrollTop;
 // private LinearLayout tabLinearLayout;
 //   private View ll_temp=null;
 //   private WindowManager manager;
 //   private boolean isShowWindow=false;
 //   private WindowManager.LayoutParams layoutParams;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_window);
        initView();
        setListener();
    }
    public void initView(){
        categoryButton=(Button)findViewById(R.id.category_btn);
        saveButton=(Button)findViewById(R.id.save_btn);
        titleEdit=(EditText)findViewById(R.id.title_edit);
        contentEdit=(EditText)findViewById(R.id.content_edit);
        dateHelper=new DateHelper(this);
        database=dateHelper.getWritableDatabase();
        getDate();

 /*       dateHelper=new DateHelper(this);
//        initDate();
        database=dateHelper.getWritableDatabase();
        cursor=database.query("memo_data",null,null,null
                ,null,null,null);
        if(cursor.moveToFirst()) {
            cursor.moveToFirst();
            System.out.print("not null");
            titleText = cursor.getString(cursor.getColumnIndex("title"));
            contentText = cursor.getString(cursor.getColumnIndex("content"));
            titleEdit.setText(titleText);
            contentEdit.setText(contentText);
        }*/



   //     myScrollView=(MyScrollView)findViewById(R.id.scroll_view);
   //     myScrollView.setOnScrollListener(this);
     //   tabLinearLayout=(LinearLayout)findViewById(R.id.content_linear);
    //    manager=(WindowManager)getSystemService(Context.WINDOW_SERVICE);
    }
    public void getDate(){
        Intent intent=this.getIntent();
        titleText=intent.getStringExtra("title");
        contentText=intent.getStringExtra("content");
        titleEdit.setText(titleText);
        contentEdit.setText(contentText);
    }
    public void setListener(){
        categoryButton.setOnClickListener(this);
        saveButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        int id =view.getId();
        switch (id){
            case R.id.category_btn:
                hasCategory();
                if(!hasCategory()) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(this);
                    AlertDialog alertDialog=builder.create();
                 /*   builder.setTitle("添加类别");
                    final EditText editText = new EditText(this);
                    editText.setSingleLine();
                    editText.setText("类别1");
                    editText.setSelection(0, 2);
                    builder.setView(editText);
                    builder.setNegativeButton("取消", null);
                    builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    });
                    AlertDialog alertDialog = builder.create();
                    alertDialog.show();*/
                 alertDialog.show();
                 View view1=getLayoutInflater().inflate(R.layout.category_dialog,null);
                    Window window=alertDialog.getWindow();
                    window.setGravity(Gravity.CENTER);
                    window.setContentView(view1);
                    EditText editText=(EditText)window.findViewById(R.id.category_edit);
                    Button cancelButton=(Button)window.findViewById(R.id.cancel);
                    Button categoryAdd=(Button)window.findViewById(R.id.category_add);

                }
                break;
            case R.id.save_btn:
                titleText=titleEdit.getText().toString();
                contentText=contentEdit.getText().toString();
                ContentValues dateValue=new ContentValues();
                dateValue.put("title",titleText);
                dateValue.put("content",contentText);
                database.update("memo_data",dateValue,"create_time=?",new String[]{"11"});
                Intent intent=new Intent(EditActivity.this,MainActivity.class);

                startActivity(intent);
                break;
        }
    }
public boolean hasCategory(){
    cursor=database.query("memo_data",new String[]{"category","COUNT(_id) AS count"},
            null,null,"category",null,null);
    if(cursor.moveToNext()){
        String string=cursor.getString(cursor.getColumnIndex("category"));
        list.add(string);
    }
    if(list.size()>1) return true;
    else return false;
}
 /*   @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if(hasFocus){
            tabHeight=tabLinearLayout.getHeight();
            tabTop=tabLinearLayout.getTop();
            scrollTop=myScrollView.getTop();
        }
    }

    @Override
    public void onScroll(int scrollY) {
        if(scrollY>tabTop){
            if(!isShowWindow){
                showWindow();
            }
        }
        else if(isShowWindow)
        {
            removeWindow();
        }
    }
    public void removeWindow() {
        if (ll_temp != null) {
            manager.removeView(ll_temp);
            isShowWindow = false;
        }
    }
    public void showWindow(){
        if(ll_temp==null){
            ll_temp= LayoutInflater.from(EditActivity.this).inflate(R.layout.tab_layout,null);
        }
        if(layoutParams==null){
            layoutParams=new WindowManager.LayoutParams(WindowManager.LayoutParams.TYPE_PHONE);
            layoutParams.format= PixelFormat.RGBA_8888;
            layoutParams.flags =WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE
            |WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL;
            layoutParams.gravity= Gravity.TOP;
            layoutParams.width=WindowManager.LayoutParams.MATCH_PARENT;
            layoutParams.height=tabHeight;
            layoutParams.x=10;
            layoutParams.y=scrollTop;
        }
        manager.addView(ll_temp,layoutParams);
        isShowWindow=true;
    }*/
 public void insertDate(){
     ContentValues dateValue=new ContentValues();
     dateValue.put("create_time","2017-09-13 05:30:30");
     dateValue.put("title",titleText);
     dateValue.put("content",contentText);
     dateValue.put("clock_is",false);
     dateValue.put("clock_time","2017-09-14 05:30:30");
     dateValue.put("music_name",musicName);
     dateValue.put("edit_last","2016-09-13 05:30:30");
     database=dateHelper.getWritableDatabase();
     database.insert("memo_data",null,dateValue);
 }
 public void initDate(){
     ContentValues dateValue=new ContentValues();
     dateValue.put("create_time","11");
     dateValue.put("title","fsadf");
     dateValue.put("content","fasffasd");
     dateValue.put("clock_is","0");
     dateValue.put("clock_time","11");
     dateValue.put("music_name","fasdf");
     dateValue.put("edit_last","11");
     database=dateHelper.getWritableDatabase();
     database.insert("memo_data",null,dateValue);
     dateValue.clear();
 }
}
