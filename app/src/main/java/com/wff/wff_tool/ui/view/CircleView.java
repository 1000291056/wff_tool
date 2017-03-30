package com.wff.wff_tool.ui.view;

import android.animation.TypeEvaluator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by wufeifei on 2017/3/23.
 */

public class CircleView extends View {
    private static final int maxlength = 100;//移动最大距离
    private MotionEvent downM;
    private MotionEvent moveM;
    private MotionEvent upM;

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.save();
        canvas.translate(getWidth() / 2, getHeight() / 2);
        Paint p = new Paint();
        p.setColor(Color.BLUE);
        p.setStyle(Paint.Style.FILL_AND_STROKE);
        canvas.drawCircle(0, 0, 20, p);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        ValueAnimator valueAnimator = ValueAnimator.ofFloat(0, 200);
        int[] xs = new int[2];

        switch (event.getAction())

        {
            case MotionEvent.ACTION_DOWN:
                downM = event;
                break;
//            case MotionEvent.ACTION_MOVE:
//                moveM = event;
//                break;
            case MotionEvent.ACTION_UP:
                upM = event;
                valueAnimator.setFloatValues(downM.getX(), upM.getX());
                valueAnimator.setDuration(1000);
                valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                    @Override
                    public void onAnimationUpdate(ValueAnimator animation) {
                        float x = (float) animation.getAnimatedValue();
                        CircleView.this.setX(x);
                    }
                });
                valueAnimator.setTarget(this);
                valueAnimator.start();
                break;
        }

        return true;
    }

    public CircleView(Context context) {
        super(context);
    }

    public CircleView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public CircleView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

}
