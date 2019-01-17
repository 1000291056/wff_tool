package com.wff.androidtool.ui.view;

import android.content.Context;
import android.graphics.drawable.Animatable;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.v4.view.ViewCompat;
import android.support.v4.widget.ViewDragHelper;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.orhanobut.logger.Logger;
import com.wff.androidtool.R;

/**
 * 只要3个子view 模拟head content foot
 */
class MyLineLayout extends LinearLayout {
    private ViewDragHelper mViewDragHelper;
    private int dispalyWidth, dispalyHeight;
    private int headOriginTop, headOriginLeft, mHeadHeight;
    private ImageView img;
    private LinearLayout head_ll, h_c_ll;
    private Handler mH = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 0:
                    startAnimator();
                    break;
                case 1:
                    stopAnimator();
                    mViewDragHelper.smoothSlideViewTo(h_c_ll, 0, -head_ll.getMeasuredHeight());
                    invalidate();
                    break;
            }
        }
    };
    Animatable d;

    private void startAnimator() {
        if (d == null) {
            d = (Animatable) img.getDrawable();
        }
        if (d.isRunning()) {
            return;
        }
        d.start();
        mH.sendEmptyMessageDelayed(1, 5000);
    }

    private void stopAnimator() {
        if (d != null && d.isRunning()) {
            d.stop();
        }

    }

    private View captureView;

    private void init() {
        WindowManager windowManager = (WindowManager) getContext().getSystemService(Context.WINDOW_SERVICE);
        dispalyWidth = windowManager.getDefaultDisplay().getWidth();
        dispalyHeight = windowManager.getDefaultDisplay().getHeight();
        mViewDragHelper = ViewDragHelper.create(this, 1f, new ViewDragHelper.Callback() {
            @Override
            public boolean tryCaptureView(@NonNull View view, int i) {
                Logger.d("capture*********content");
                return view == content || view == h_c_ll;
            }

            @Override
            public int clampViewPositionHorizontal(View child, int left, int dx) {
                //child 表示当前正在移动的view
                //left 表示当前的view正要移动到左边距为left的地方
                //dx 表示和上一次滑动的距离间隔
                //返回值就是child要移动的目标位置.可以通过控制返回值,从而控制child只能在ViewGroup的范围中移动.
                return 0;
            }

            private int lastState = -1;
            private int curState = -1;

            @Override
            public void onViewDragStateChanged(int state) {
                super.onViewDragStateChanged(state);
                curState = state;
                if (lastState == ViewDragHelper.STATE_DRAGGING && lastState != state) {
//停止拖动
                }
                if (state == ViewDragHelper.STATE_DRAGGING) {

                }
                lastState = state;
                Logger.d("capture状态 :%d", state);
            }

            @Override
            public int clampViewPositionVertical(View child, int top, int dy) {
                //child 表示当前正在移动的view
                //top 表示当前的view正要移动到上边距为top的地方
                //dx 表示和上一次滑动的距离间隔
//                if (child == content) {
//                    return top > foot.getMeasuredHeight() ? top : foot.getMeasuredHeight();
//                }
//                if (top<=head_ll.getMeasuredHeight()){
//                    startAnimator();
//                }
                return top;
            }

            @Override
            public void onViewReleased(@NonNull View releasedChild, float xvel, float yvel) {

                if (releasedChild == content) {
                    mH.sendEmptyMessage(0);
                    invalidate();
                } else {
                    super.onViewReleased(releasedChild, xvel, yvel);
                }
            }

            @Override
            public int getViewVerticalDragRange(@NonNull View child) {
                return head_ll.getMeasuredHeight();
            }
        });
        mViewDragHelper.setEdgeTrackingEnabled(ViewDragHelper.EDGE_ALL);
    }

    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);
//        if (l >)
    }

    @Override
    public void computeScroll() {
        //固定写法
        //此方法用于自动滚动,比如自动回滚到默认位置.
        if (mViewDragHelper.continueSettling(true)) {
            ViewCompat.postInvalidateOnAnimation(this);
        }
    }


    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return mViewDragHelper.shouldInterceptTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        mViewDragHelper.processTouchEvent(event);
        return true;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    View head, content, foot;

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
//
        content = getChildAt(0);
        img = findViewById(R.id.img);
        head_ll = findViewById(R.id.head_ll);
        h_c_ll = findViewById(R.id.h_c_ll);
        mHeadHeight = head_ll == null ? 0 : head_ll.getMeasuredHeight();
        headOriginTop = content.getTop();
        headOriginLeft = content.getLeft();
        mViewDragHelper.smoothSlideViewTo(h_c_ll, 0, -head_ll.getMeasuredHeight());
    }

    public MyLineLayout(Context context) {
        super(context);
        init();
    }

    public MyLineLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public MyLineLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }
}
