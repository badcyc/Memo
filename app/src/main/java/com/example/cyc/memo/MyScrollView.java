package com.example.cyc.memo;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ScrollView;

/**
 * Created by cyc on 17-9-15.
 */

public class MyScrollView extends ScrollView {

    private OnScrollListener listener;
    public void setOnScrollListener(OnScrollListener listener){
        this.listener=listener;
    }
    public MyScrollView(Context context){
        this(context,null);
    }
    public MyScrollView(Context context, AttributeSet attrs){
        this(context,attrs,0);
    }
    public MyScrollView(Context context,AttributeSet attrs,int defStyle){
        super(context,attrs,defStyle);
    }
    public interface OnScrollListener{
        void onScroll(int scrollY);
    }

    @Override
    public void computeScroll() {
        super.computeScroll();
        if(listener!=null){
            listener.onScroll(getScrollY());
        }
    }
}
