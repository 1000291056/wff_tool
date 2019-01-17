package com.wff.androidtool.ui.view;

import android.opengl.GLSurfaceView;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

/**
 * Created by wufeifei on 2017/3/27.
 */

public class MyRender implements GLSurfaceView.Renderer {
    private TriAngle mTriAngle;

    @Override
    public void onSurfaceCreated(GL10 gl, EGLConfig config) {
        mTriAngle = new TriAngle();
        gl.glClearColor(0.5f, 0.5f, 0.5f, 1.0f);//设置背景色
        mTriAngle = new TriAngle();
//        gl.glViewport();
    }

    @Override
    public void onSurfaceChanged(GL10 gl, int width, int height) {
        gl.glViewport(0, 0, width, height);
    }

    @Override
    public void onDrawFrame(GL10 gl) {
        gl.glClear(GL10.GL_CLEAR);//重绘背景色
        mTriAngle.draw();
    }
}
