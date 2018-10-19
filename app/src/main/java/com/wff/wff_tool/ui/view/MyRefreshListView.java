package com.wff.wff_tool.ui.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.widget.LinearLayout;

import com.wff.wff_tool.R;

/**
 * Created by wufeifei on 2017/3/30.
 */

public class MyRefreshListView extends RecyclerView {
    @Override
    public boolean onInterceptTouchEvent(MotionEvent e) {
        return super.onInterceptTouchEvent(e);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        return super.dispatchTouchEvent(ev);
    }

    private LinearLayoutManager mLayoutManager;
    private RecyclerView.OnScrollListener mOnScrollListener = new RecyclerView.OnScrollListener() {
        private int lastVisiablePosition;
        private int firstVisiablePosition;

        @Override
        public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
            super.onScrollStateChanged(recyclerView, newState);
            if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                if (firstVisiablePosition == 0) {
                } else {

                }
            }
        }

        @Override
        public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
            super.onScrolled(recyclerView, dx, dy);
            lastVisiablePosition = mLayoutManager.findLastCompletelyVisibleItemPosition();
            firstVisiablePosition = mLayoutManager.findFirstCompletelyVisibleItemPosition();
        }
    };

    private void init() {

        mLayoutManager = new LinearLayoutManager(getContext());
        setLayoutManager(mLayoutManager);
        addOnScrollListener(mOnScrollListener);
    }

    public MyRefreshListView(Context context) {
        super(context);
        init();
    }

    public MyRefreshListView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public MyRefreshListView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);
    }

    class HeadView extends LinearLayout {
        private void init() {
            LayoutInflater.from(getContext()).inflate(R.layout.head_layout, this);
        }

        public HeadView(Context context) {
            super(context);
            init();
        }

        public HeadView(Context context, @Nullable AttributeSet attrs) {
            super(context, attrs);
            init();
        }

        public HeadView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
            super(context, attrs, defStyleAttr);
            init();
        }
    }
}
