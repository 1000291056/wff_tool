package com.wff.androidtool.ui.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.widget.ScrollerCompat;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;

/**
 * Created by wufeifei on 2017/3/23.
 */

public class PullRefreshView extends LinearLayout {
    private HeadView mHeadView;
    private View mFootView;
    private OnPullDownListener mOnPullDownListener;
    private OnPullUpListener mOnPullUpListener;
    private ScrollerCompat mScrollerCompat;
    private int lastX;//触屏按下 相对窗口坐标
    private int lasty;
    private int startx;
    private int starty;
    private Direct mDirect;

    /**
     * 向下拉
     */
    interface OnPullDownListener {
        void onRefresh();
    }

    /**
     * 向上拉
     */
    interface OnPullUpListener {
        void onLoadMore();
    }

    public PullRefreshView(Context context) {
        super(context);
        init();
    }

    public PullRefreshView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public PullRefreshView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mScrollerCompat = ScrollerCompat.create(getContext());
        mHeadView = new HeadView(getContext());
        this.addView(mHeadView, 0);

    }


    @Override
    public void computeScroll() {
        super.computeScroll();
        if (mScrollerCompat.computeScrollOffset()) {
            this.scrollTo(0, mScrollerCompat.getCurrY());

        }
    }

    private void smoothScrollTo(int dX, int dY) {
        if (mDirect == Direct.DOWN && -getScrollY() > mHeadView.getMeasuredHeight()) {
            return;
        }
        int scrollX = this.getScrollX();
        int scrollY = this.getScrollY();
        mScrollerCompat.startScroll(scrollX, scrollY, dX, dY, 0);
        invalidate();
    }

    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);
//        com.orhanobut.logger.Logger.e("onScrollChanged    l=%d t=%d" , l,t);
        View content=getChildAt(0);//表示内容的子view
        com.orhanobut.logger.Logger.e("onScrollChanged    getHeight()=%d getScrollY()=%d getMeasuredHeight()=%d" , getHeight(),getScrollY(),getMeasuredHeight());
        com.orhanobut.logger.Logger.e("子onScrollChanged    getHeight()=%d getScrollY()=%d getMeasuredHeight()=%d" , content.getHeight(),content.getScrollY(),content.getMeasuredHeight());
        if (t > -mHeadView.getMeasuredHeight() && t < 0) {
            mHeadView.setVisibility(View.VISIBLE);
            mHeadView.startMyAnimation();
        } else {
//            mHeadView.hideHead();
        }
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return super.onInterceptTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {//处理触摸事件
        int action = event.getAction();
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                lastX = (int) event.getX();
                lasty = (int) event.getY();
                break;
            case MotionEvent.ACTION_MOVE:
                if (event.getY() > lasty) {
                    mDirect = Direct.DOWN;
                } else {
                    mDirect = Direct.UP;
                }
                smoothScrollTo(lastX - (int) event.getX(), lasty - (int) event.getY());
                lastX = (int) event.getX();
                lasty = (int) event.getY();
                break;
            case MotionEvent.ACTION_UP:
//                smoothScrollTo(lastX-(int) event.getX(), lasty-(int) event.getY());
                break;
        }
        return true;
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        com.orhanobut.logger.Logger.d("mode:"+MeasureSpec.getMode(widthMeasureSpec)+"   size:"+MeasureSpec.getSize(widthMeasureSpec));
    }

    enum Direct {
        UP, DOWN;
    }

}
