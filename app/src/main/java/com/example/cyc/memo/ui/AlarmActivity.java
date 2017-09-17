package com.example.cyc.memo.ui;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TimePicker;

import com.example.cyc.memo.R;

/**
 * Created by cyc on 17-9-17.
 */

public class AlarmActivity extends AppCompatActivity {
    private TimePicker timePicker;
    private Button dateButton;
    private LinearLayout alarmContainer;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.alarm_activity);
        alarmContainer=(LinearLayout)findViewById(R.id.alarm_container);
        timePicker=(TimePicker)findViewById(R.id.time_picker);
        dateButton=(Button)findViewById(R.id.date_choose);
        dateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PopupWindow popupWindow=new PopupWindow(AlarmActivity.this);
                popupWindow.setWidth(WindowManager.LayoutParams.MATCH_PARENT);
                popupWindow.setHeight(WindowManager.LayoutParams.WRAP_CONTENT);
                popupWindow.setTouchable(true);
                popupWindow.setFocusable(true);
                popupWindow.setOutsideTouchable(true);
              //  popupWindow.setAnimationStyle(R.sty);
                View view1=getLayoutInflater().inflate(R.layout.date_dialog,null);
                popupWindow.setContentView(view1);
                popupWindow.showAtLocation(alarmContainer,Gravity.BOTTOM,0,0);

            }
        });
        timePicker.setIs24HourView(true);
    }
}
