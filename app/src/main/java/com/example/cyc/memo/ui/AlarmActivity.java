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

                LayoutInflater inflater=(LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE);

                View view1=inflater.inflate(R.layout.date_dialog,null);
                PopupWindow popupWindow=new PopupWindow(view1, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT
                );
              //  popupWindow.setHeight(LinearLayout.LayoutParams.WRAP_CONTENT);
               // popupWindow.setWidth(LinearLayout.LayoutParams.MATCH_PARENT);
                popupWindow.setTouchable(true);
                popupWindow.setOutsideTouchable(true);
               // popupWindow.setContentView(view1);
                popupWindow.showAtLocation(alarmContainer, Gravity.BOTTOM,0,0);

            }
        });
        timePicker.setIs24HourView(true);
    }
}
