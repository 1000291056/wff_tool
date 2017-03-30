package com.wff.wff_tool;

import android.animation.ValueAnimator;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;

import com.wff.wff_tool.ui.view.CircleView;

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
        ValueAnimator valueAnimator = ValueAnimator.ofFloat(0, 20);//时间
        final float g = 9.8f;
        valueAnimator.setInterpolator(new LinearInterpolator());
        valueAnimator.setDuration(2000);
        valueAnimator.setRepeatMode(ValueAnimator.RESTART);
        valueAnimator.setRepeatCount(-1);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float time = (float
                        ) animation.getAnimatedValue();
                float h = 20 * time;
                float v = (float) (0.5 * g * time * time);
                mCircle.setX(h);
                mCircle.setTranslationY(v);
                mCircle.setAlpha(1 - time / 10);
                mImv.setScaleY(time / 20);
            }
        });
        valueAnimator.start();
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
