package com.wff.wff_tool.ui.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;

import com.orhanobut.logger.Logger;

/**
 * Created by wufeifei on 2017/3/30.
 */

public class ParentView extends LinearLayout {
    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        Logger.e("dispatchTouchEvent");
//        return true;
//        return false;
        return super.dispatchTouchEvent(event);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        //        return true;
//        return false;
        return super.onTouchEvent(event);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        //        return true;
//        return false;
        return super.onInterceptTouchEvent(ev);
    }

    public ParentView(Context context) {
        super(context);
    }

    public ParentView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public ParentView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
}
