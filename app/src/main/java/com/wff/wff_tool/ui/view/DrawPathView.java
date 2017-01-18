package com.wff.wff_tool.ui.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import java.io.Serializable;

/**
 * Created by wufeifei on 2016/12/9.
 */

public class DrawPathView extends View {
    private static final String TAG = DrawPathView.class.getSimpleName();
    /**
     * 指针摆动角度
     */
    private static final int SWINGANGLESCALE = 2;
    /**
     * 刻度长度
     */
    private static final int SCALELENGTH = 25;
    private static final int POSTINVALIDATE = 0;
    private static final int STARTSWING = 1;
    private Context mContext;
    private Paint mPaint;
    private boolean isFirst = true;
    /**
     * 旋转的角度
     */
    private double mIndicatorAngle = -90;
    private double mfinalIndicatorAngle = -90;
    /**
     * 以x轴为基准指针的角度
     */
    private double realAngle = 90;
    private int scale = SWINGANGLESCALE;
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case POSTINVALIDATE:
                    Swing swing = (Swing) msg.obj;
                    mIndicatorAngle = swing.getAngel();
                    invalidate();
                    break;
            }
        }
    };

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        setMeasuredDimension(widthMeasureSpec, heightMeasureSpec);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Path path = new Path();


        canvas.translate(getWidth() / 2, getHeight() / 2);
        mPaint.setStyle(Paint.Style.STROKE);

        ////画半圆
        mPaint.setColor(Color.GREEN);
        canvas.save();
        int r = getWidth() < getHeight() ? getWidth() / 2 : getHeight() / 2;
        canvas.drawArc(-r, -r, r, r, 0, -180, false, mPaint);
        canvas.restore();
        ////end
        mPaint.setStyle(Paint.Style.FILL_AND_STROKE);
        ////坐标轴
        canvas.save();
        mPaint.setColor(Color.GREEN);
        canvas.drawLine(-getWidth() / 2, 0, getWidth() / 2, 0, mPaint);

        canvas.restore();
        ////刻度
        int temp = 90;
        mPaint.setColor(Color.BLACK);
        mPaint.setStrokeWidth(5);
        canvas.save();
        int length = (getWidth() / 2 > getHeight() / 2 ? getHeight() / 2 : getWidth() / 2);
        canvas.rotate(90);

        while (temp >= -90) {
            canvas.drawLine(0, length - SCALELENGTH, 0, length, mPaint);
            canvas.rotate(10);
            temp -= 10;
        }
        canvas.restore();
        ////写刻度
        canvas.save();
        Paint paintScaleText = new Paint();
        paintScaleText.setStrokeWidth(0);
        paintScaleText.setStyle(Paint.Style.STROKE);
        paintScaleText.setColor(Color.RED);
        paintScaleText.setTextSize(25);
        temp = 90;
        int tempAngle = 180;
        canvas.rotate(90);
        while (temp >= -90) {
            String text = String.valueOf(tempAngle);
            canvas.drawText(text, -paintScaleText.measureText(text) / 2, -(length - SCALELENGTH - 20), paintScaleText);
            canvas.rotate(-10);
            temp -= 10;
            tempAngle -= 10;
        }

        canvas.restore();
        ////
        ////路径 指针
        mPaint.setColor(Color.RED);
        canvas.save();
        canvas.rotate((float) mIndicatorAngle);
        path.moveTo(-5, 30);
        path.lineTo(5, 30);
        path.lineTo(0, getHeight() > getWidth() ? -getWidth() / 2 + 10 : -getHeight() / 2 + 10);
        path.close();
        canvas.drawPath(path, mPaint);
        canvas.restore();
        ////圆点
        mPaint.setColor(Color.BLACK);
        canvas.save();
        canvas.drawCircle(0, 0, 15, mPaint);
        canvas.restore();
        ////打印角度
        Paint paintText = new Paint();
        paintText.setStrokeWidth(0);
        paintText.setStyle(Paint.Style.STROKE);
        paintText.setTextSize(25);
        canvas.save();
        canvas.drawText("相对于x轴角度:" + realAngle, 0, -(getWidth() / 2 + 50), paintText);
        canvas.restore();


    }

    /**
     * 指针摆动控制
     *
     * @param angle 最终角度
     */
    private void swing(double angle) {

        double indicatorAngle;
        int scale = SWINGANGLESCALE;
        int timeS = 1;
        while (scale > 0) {
            for (int i = 0; i < 2; i++) {
                timeS++;
                if (i == 0) {
                    indicatorAngle = (mIndicatorAngle + 2 * scale);

                } else {
                    indicatorAngle = (mIndicatorAngle - 2 * scale);
                }
                Message messageTEMP2 = mHandler.obtainMessage();
                messageTEMP2.what = POSTINVALIDATE;
                messageTEMP2.obj = new Swing(indicatorAngle);
                mHandler.sendMessageDelayed(messageTEMP2, timeS * 100);
            }
            scale -= 0.5;
        }
        Message messageTEMP2 = mHandler.obtainMessage();
        messageTEMP2.what = POSTINVALIDATE;
        messageTEMP2.obj = new Swing(mfinalIndicatorAngle);
        mHandler.sendMessageDelayed(messageTEMP2, timeS * 100);
    }

    /**
     * 计算相对于x轴 指针角度
     */
    private void calculateRealAngle() {
        realAngle = (mfinalIndicatorAngle + 90);
        Log.i(TAG, "--------------------------realAngle=" + realAngle);
    }

    public DrawPathView(Context context) {
        super(context);
        init(context, null);
    }

    public DrawPathView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public DrawPathView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    /**
     * 初始化参数
     *
     * @param context
     * @param attrs
     */
    private void init(Context context, AttributeSet attrs) {
        this.mContext = context;
        mPaint = new Paint();
        if (attrs != null) {//// TODO: 2016/12/9

        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
            case MotionEvent.ACTION_MOVE:
                caculateAngle(event.getX(), event.getY());
                break;
            case MotionEvent.ACTION_UP:
                swing(mfinalIndicatorAngle);
                break;
        }
        calculateRealAngle();
        return true;
    }

    /**
     * 计算旋转角度
     *
     * @param x
     * @param y
     */
    private void caculateAngle(float x, float y) {
        if (y >= getHeight() / 2) {
            if (x >= getWidth() / 2) {
                mfinalIndicatorAngle = mIndicatorAngle = 90;
            } else {
                mfinalIndicatorAngle = mIndicatorAngle = -90;
            }
        } else {
            mfinalIndicatorAngle = mIndicatorAngle = -Math.atan(1.0 * (x - getWidth() / 2) / (y - getHeight() / 2)) * 180 / Math.PI;
        }
        Log.i(TAG, "--------------------------mIndicatorAngle=" + mIndicatorAngle);
        invalidate();
    }

    public double getRealAngle() {
        return realAngle;
    }

    public void setRealAngle(double realAngle) {
        this.realAngle = realAngle;
        mfinalIndicatorAngle = mIndicatorAngle = 90 - realAngle;
    }

    class Swing implements Serializable {
        private double angel;

        public Swing(double angel) {
            this.angel = angel;
        }

        public double getAngel() {
            return angel;
        }

        public void setAngel(double angel) {
            this.angel = angel;
        }
    }
}
