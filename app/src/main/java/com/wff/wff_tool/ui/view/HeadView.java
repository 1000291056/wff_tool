package com.wff.wff_tool.ui.view;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.support.annotation.IntDef;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.wff.wff_tool.R;

import butterknife.BindView;

/**
 * Created by wufeifei on 2017/3/24.
 */

public class HeadView extends LinearLayout {
    ImageView mImv;

    private void init() {
        LayoutInflater.from(getContext()).inflate(R.layout.pull_head_layout, HeadView.this);
        mImv = (ImageView) findViewById(R.id.imv);
//        startMyAnimation();
        initAnimator();
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

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        LayoutParams p = (LayoutParams) this.getLayoutParams();
        p.topMargin = -this.getMeasuredHeight();
        this.setLayoutParams(p);
    }

    ValueAnimator valueAnimator;

    private void initAnimator() {
        valueAnimator = ValueAnimator.ofFloat(0f, 360f);
        valueAnimator.setTarget(mImv);
        valueAnimator.setDuration(500);
        valueAnimator.setRepeatCount(ValueAnimator.INFINITE);
        valueAnimator.setRepeatMode(ValueAnimator.RESTART);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float v = (float) animation.getAnimatedValue();
                mImv.setRotation(v);
            }
        });
        hideenValueAnimator = ValueAnimator.ofFloat(1f, 0f);
        hideenValueAnimator.setTarget(this);
        hideenValueAnimator.setDuration(500);
        hideenValueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float v = (float) animation.getAnimatedValue();
                HeadView.this.setAlpha(v);
                if (v<=0f){
                    HeadView.this.setVisibility(View.GONE);
                }
            }
        });
    }

    public void startMyAnimation() {
        if (valueAnimator.isRunning() || valueAnimator.isStarted()) {
            return;
        }
        valueAnimator.start();
    }

    ValueAnimator hideenValueAnimator;

    public void hideHead() {
        if (hideenValueAnimator.isRunning() || hideenValueAnimator.isStarted()) {
            return;
        }
        hideenValueAnimator.start();
    }

    @Override
    protected void onWindowVisibilityChanged(int visibility) {
        super.onWindowVisibilityChanged(visibility);
        if (visibility == View.VISIBLE) {
//            valueAnimator = ValueAnimator.ofFloat(0f, 1f);
//            valueAnimator.setTarget(mImv);
//            valueAnimator.setDuration(1000);
//            valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
//                @Override
//                public void onAnimationUpdate(ValueAnimator animation) {
//                    float v = (float) animation.getAnimatedValue();
//                    mImv.setScaleY(v);
//                }
//            });
        }
    }
}