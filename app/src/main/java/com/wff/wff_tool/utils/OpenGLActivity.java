package com.wff.wff_tool.utils;

import android.opengl.GLSurfaceView;
import android.os.Bundle;

import com.wff.wff_tool.BaseActivity;
import com.wff.wff_tool.ui.view.MyGLSurfaceView;

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
