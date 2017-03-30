package com.wff.wff_tool;

import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.transition.ChangeBounds;
import android.transition.ChangeImageTransform;
import android.transition.Scene;
import android.transition.TransitionInflater;
import android.transition.TransitionManager;
import android.view.ViewGroup;

public class TestTransitionActivity extends AppCompatActivity {
    Scene scene1, scene2;
    Handler mHandler = new Handler() {
        @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            TransitionManager.go(scene2,TransitionInflater.from(TestTransitionActivity.this).inflateTransition(R.transition.test_transitionset));
        }
    };

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_transition);
        ViewGroup viewGroup = (ViewGroup) findViewById(R.id.viewgroup);
        scene1 = Scene.getSceneForLayout(viewGroup, R.layout.scen1, this);
        scene2 = Scene.getSceneForLayout(viewGroup, R.layout.scen2, this);
        TransitionManager.go(scene1, TransitionInflater.from(this).inflateTransition(R.transition.test_transitionset));
        mHandler.sendEmptyMessageDelayed(0, 2000);
    }
}
