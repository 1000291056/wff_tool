package com.wff.wff_tool.ui.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Scroller;

import com.orhanobut.logger.Logger;

public class TouchEventView extends LinearLayout implements View.OnClickListener {
    private static final String TAG = TouchEventView.class.getSimpleName();
    private Scroller mScroller;

    public boolean dispatchTouchEvent(MotionEvent event) {
        Logger.d("________________dispatchTouchEvent");
        return super.dispatchTouchEvent(event);
    }

    public boolean onTouchEvent(MotionEvent event) {
        Logger.d("________________onTouchEvent");
        // return true;
        return super.onTouchEvent(event);
    }

    public boolean onInterceptTouchEvent(MotionEvent event) {
        Logger.d("________________onInterceptTouchEvent");
        return super.onInterceptTouchEvent(event);
    }

    @Override
    public void onClick(View v) {
        Logger.d("________________onClick");
    }

    private void init() {
        this.setOnClickListener(this);
        mScroller=new Scroller(getContext());
        mScroller.getCurrX();
    }

    public TouchEventView(Context context) {
        super(context);
        init();
    }

    public TouchEventView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public TouchEventView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }


}
