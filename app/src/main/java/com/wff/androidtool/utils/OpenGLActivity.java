package com.wff.androidtool.utils;

import android.opengl.GLSurfaceView;
import android.os.Bundle;

import com.wff.androidtool.component.activity.BaseActivity;
import com.wff.androidtool.ui.view.MyGLSurfaceView;

/**
 * Created by wufeifei on 2016/12/21.
 */

public class OpenGLActivity extends BaseActivity {
    private GLSurfaceView mGLView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Create a GLSurfaceView instance and set it
        // as the ContentView for this Activity.
        mGLView = new MyGLSurfaceView(this);
        setContentView(mGLView);
    }
}
