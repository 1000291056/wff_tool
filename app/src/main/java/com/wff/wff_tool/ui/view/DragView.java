package com.wff.wff_tool.ui.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Scroller;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

/**
 * Created by wufeifei on 2017/2/13.
 */

public class DragView extends View {
    private Scroller mScroller;
    private Context mContext;
    private Paint mPaint;
    private static List<Data> datas = new ArrayList();

    static {
        datas.add(new Data(1, 300, 240));
        datas.add(new Data(2, 300, 240));
        datas.add(new Data(3, 300, 240));
        datas.add(new Data(4, 300, 240));
        datas.add(new Data(5, 300, 240));
        datas.add(new Data(-6, 300, 240));
        datas.add(new Data(-7, 300, 240));
        datas.add(new Data(-8, 300, 240));
        datas.add(new Data(-9, 300, 240));
        datas.add(new Data(-10, 300, 240));
        datas.add(new Data(-11, 300, 240));
        datas.add(new Data(-12, 300, 240));

    }

    private void initialize(Context context) {
        this.mContext = context;
        mScroller = new Scroller(context);
        mPaint = new Paint();
    }

    /**
     * @param destX
     * @param destY 内容移动到触屏位置
     */
    private void smoothScrollTo(int destX, int destY) {
        int scrollX = this.getScrollX();
        int scrollY = this.getScrollY();
        int deltaX = destX - scrollX;
        int deltaY = destY - scrollY;
        com.orhanobut.logger.Logger.d("destX=%1$d      destY=%2$d     scrollX=%3$d     scrollY=%4$d     deltaX=%5$d    deltaY=%6$d", destX, destY, scrollX, scrollY, deltaX, deltaY);
        mScroller.startScroll(scrollX, scrollY, -deltaX, -deltaY);
        postInvalidate();
    }

    /**
     * @param
     * @param
     */
    private void smoothScrollBy(int deltaX, int deltaY) {
        int scrollX = this.getScrollX();
        int scrollY = this.getScrollY();
        com.orhanobut.logger.Logger.d("scrollX=%1$d     scrollY=%2$d     deltaX=%3$d    deltaY=%4$d", scrollX, scrollY, deltaX, deltaY);
        mScroller.startScroll(scrollX, scrollY, -deltaX, -deltaY);
        postInvalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.translate(20, getHeight() - 20);
        //xy轴
        canvas.save();
        mPaint.setStrokeWidth(3);
        mPaint.setColor(Color.BLACK);
        mPaint.setStyle(Paint.Style.FILL_AND_STROKE);
        canvas.drawLine(0, 0, getWidth(), 0, mPaint);
        canvas.drawLine(0, 0, 0, -getHeight(), mPaint);
        canvas.restore();
        //画数据
        canvas.save();
        mPaint.setColor(Color.GREEN);
        for (Data d : datas
                ) {
            canvas.drawRect(getRectF(d.getMonth() * 100, d.getMaxData(), d.getMinData()), mPaint);
            canvas.drawLine(d.getMonth() * 100, -d.getMaxData() - 10, d.getMonth() * 100, -d.getMinData() + 10, mPaint);
        }
        canvas.restore();

    }

    @Override
    public void computeScroll() {
        super.computeScroll();
        if (mScroller.computeScrollOffset()) {
            this.scrollTo(mScroller.getCurrX(), mScroller.getCurrY());
            postInvalidate();
        }
    }

    private int lastX;
    private int lastY;

    @Override
    public boolean onTouchEvent(MotionEvent event) {//处理触摸事件
        int action = event.getAction();
        switch (action) {

            case MotionEvent.ACTION_DOWN:
                lastX = (int) event.getX();
                lastY = (int) event.getY();
                break;
            case MotionEvent.ACTION_UP:
//                int dx = (int) (event.getX() - lastX);
//                int dy = (int) (event.getY() - lastY);
//                smoothScrollBy(dx, dy);
//                break;
            case MotionEvent.ACTION_MOVE:
                int dx1 = (int) (event.getX() - lastX);
                int dy1 = (int) (event.getY() - lastY);
                dy1=0;
                scrollBy(-dx1,-dy1);
                //smoothScrollBy(dx1, dy1);
                lastX = (int) event.getX();
                lastY = (int) event.getY();
                break;
        }
        return true;
    }

    private RectF getRectF(float x, float max, float min) {
        final int width = 10;
        float left = 0, top = 0, right = 0, bottom = 0;
        left = x - width / 2;
        top = max;
        right = x + width / 2;
        bottom = min;
        return new RectF(left, -top, right, -bottom);
    }

    public DragView(Context context) {
        super(context);
        initialize(context);
    }

    public DragView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initialize(context);
    }


    public DragView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initialize(context);
    }

    static class Data {
        private float month, maxData, minData;

        public Data(float month, float maxData, float minData) {
            this.month = month;
            this.maxData = maxData;
            this.minData = minData;
        }

        public float getMonth() {
            return month;
        }

        public float getMaxData() {
            return maxData;
        }

        public float getMinData() {
            return minData;
        }
    }
}
