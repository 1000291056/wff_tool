package com.wff.wff_tool.ui.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

import com.orhanobut.logger.Logger;
import com.wff.wff_tool.R;

public class CustomLineLayout extends ViewGroup {
    private float margin, margin_t, margin_r, margin_b, margin_l = 0;


    private void initAttrs(AttributeSet attrs) {
        TypedArray typedArray = getContext().obtainStyledAttributes(attrs, R.styleable.custom_stytle);
        margin = typedArray.getDimension(R.styleable.custom_stytle_margin, 0);
        if (margin > 0) {
            margin_b = margin_r = margin_l = margin_t = margin;
        } else {
            margin_t = typedArray.getDimension(R.styleable.custom_stytle_margin_top, 0);
            margin_r = typedArray.getDimension(R.styleable.custom_stytle_margin_right, 0);
            margin_b = typedArray.getDimension(R.styleable.custom_stytle_margin_botom, 0);
            margin_l = typedArray.getDimension(R.styleable.custom_stytle_margin_left, 0);
        }
    }

    public CustomLineLayout(Context context) {
        super(context);
    }

    public CustomLineLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        initAttrs(attrs);
    }

    public CustomLineLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initAttrs(attrs);
    }


    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int totalWidth = getMeasuredWidth();
        int totalHeight = getMeasuredHeight();
        int curX = (int) margin_l;//当前已经布局到的位置 左上角顶点
        int curY = 0;
        int p_l, p_t, p_r, p_b = 0;
        for (int index = 0; index < getChildCount(); index++) {
            View v = getChildAt(index);
            p_l = (int) curX;
            p_t = (int) (curY + margin_t);
            p_r = p_l + v.getMeasuredWidth();
            p_b = p_t + v.getMeasuredHeight();
//            Logger.d("p_l=%d,p_t=%d,p_r=%d,p_b=%d",p_l,p_t,p_r,p_t);
            v.layout(p_l, p_t, p_r, p_b);
            curX = p_l;
            curY = (int) (p_b+margin_b);

        }
    }

    /**
     * 需要父容器去测量子view
     *
     * @param widthMeasureSpec
     * @param heightMeasureSpec
     */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        for (int i = 0; i < getChildCount(); i++) {
            measureChild(getChildAt(i), widthMeasureSpec, heightMeasureSpec);
        }
    }
}
