package com.wff.androidtool.component.activity;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;

import com.wff.androidtool.R;
import com.wff.androidtool.ui.view.CircleView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TestAnimatorActivity extends AppCompatActivity {

    Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            startAnimator();
        }
    };
    @BindView(R.id.circle)
    CircleView mCircle;
    @BindView(R.id.imv)
    ImageView mImv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_animator_layout);
        ButterKnife.bind(this);
        mHandler.sendEmptyMessageDelayed(0, 1000);
    }

    private void startAnimator() {
        final ValueAnimator valueAnimator = ValueAnimator.ofFloat(0, 20);//时间
        final float g = 9.8f;
        valueAnimator.setInterpolator(new LinearInterpolator());
        valueAnimator.setDuration(2000);
        valueAnimator.setRepeatMode(ValueAnimator.RESTART);
        valueAnimator.setRepeatCount(-1);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                if (mCircle.getVisibility() != View.VISIBLE) {
                    mCircle.setVisibility(View.VISIBLE);
                }
                float time = (float
                        ) animation.getAnimatedValue();
                float h = 20 * time;
                float v = (float) (0.5 * g * time * time);
                mCircle.setX(h);
                mCircle.setTranslationY(v);
                mCircle.setAlpha(1 - time / 10);
//                mImv.setScaleY(time / 20);
            }
        });
//        objectAnimator.se
        final int originHeight = mImv.getMeasuredHeight();
        ValueAnimator heightValueAnimator = ValueAnimator.ofFloat(1f, 0f);
        heightValueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                if (mImv.getVisibility() != View.VISIBLE) {
                    mImv.setVisibility(View.VISIBLE);
                }

                float fraction = (float) animation.getAnimatedValue();
                mImv.getLayoutParams().height = (int) (fraction * originHeight);
                mImv.requestLayout();
            }
        });

        heightValueAnimator.setDuration(1500);
        heightValueAnimator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                valueAnimator.start();
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
        heightValueAnimator.start();

    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return super.onTouchEvent(event);
    }
}
