package com.wff.wff_tool.ui.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.View;

import java.io.Serializable;

/**
 * Created by wufeifei on 2016/12/9.
 */

public class DrawPathView extends View {
    /**
     * 指针摆动角度
     */
    private static final int SWINGANGLESCALE = 5;
    private static final int POSTINVALIDATE = 0;
    private static final int STARTSWING = 1;
    private Context mContext;
    private Paint mPaint;
    private boolean isFirst = true;
    private int mIndicatorAngle = 0;
    private int mfinalIndicatorAngle = 0;
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

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Path path = new Path();

        canvas.save();
        canvas.translate(getWidth() / 2, getHeight() / 2);
        mPaint.setStyle(Paint.Style.FILL_AND_STROKE);
        ////坐标轴
        mPaint.setColor(Color.GREEN);
        canvas.drawLine(-getWidth() / 2, 0, getWidth() / 2, 0, mPaint);
        canvas.drawLine(0, -getHeight() / 2, 0, getHeight() / 2, mPaint);
        mPaint.setColor(Color.RED);
        ////路径
        canvas.rotate(mIndicatorAngle);
        path.moveTo(-10, 30);
        path.lineTo(10, 30);
        path.lineTo(0, getHeight() > getWidth() ? -getWidth() / 2 + 10 : -getHeight() / 2 + 10);
        path.close();
        canvas.drawPath(path, mPaint);
        ////圆
        mPaint.setColor(Color.BLACK);
        canvas.drawCircle(0, 0, 15, mPaint);
        canvas.restore();
        if (isFirst) {
            swing(mIndicatorAngle);
            isFirst = false;
        }


    }

    /**
     * 指针摆动控制
     *
     * @param angle 最终角度
     */
    private void swing(int angle) {

        int indicatorAngle;
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

    class Swing implements Serializable {
        private int angel;

        public Swing(int angel) {
            this.angel = angel;
        }

        public int getAngel() {
            return angel;
        }

        public void setAngel(int angel) {
            this.angel = angel;
        }
    }
}
