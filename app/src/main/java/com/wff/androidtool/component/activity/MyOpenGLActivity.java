package com.wff.androidtool.component.activity;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;
import android.content.pm.ConfigurationInfo;
import android.opengl.GLES10;
import android.opengl.GLES20;
import android.opengl.GLSurfaceView;
import android.os.Build;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import com.wff.androidtool.opengles.Triangle;
import com.wff.androidtool.ui.view.OpenGLRenderer;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import static javax.microedition.khronos.opengles.GL10.GL_COLOR_BUFFER_BIT;

/**
 * @author ffw
 */
public class MyOpenGLActivity extends Activity {
    private GLSurfaceView mGlSurfaceView;
    private boolean isSupportOpenGl = false;

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        if (isSupportOpenGl = supportOpenGl()) {
            mGlSurfaceView = new GLSurfaceView(this);
            mGlSurfaceView.setEGLContextClientVersion(2);
            mGlSurfaceView.setRenderer(new MyRender(this));
            setContentView(mGlSurfaceView);
        } else {
            setContentView(new TextView(this));
        }

    }

    @Override
    protected void onResume() {
        super.onResume();
        if (isSupportOpenGl) {
            mGlSurfaceView.onResume();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (isSupportOpenGl) {
            mGlSurfaceView.onPause();
        }
    }

    /**
     *
     */
    static class MyRender implements GLSurfaceView.Renderer {
        /**
         * 绘制的三角形
         */
        private Triangle mTriangle;
        private Context mContext;

        public MyRender(Context context) {
            mContext = context;
        }

        /**
         * 当Surface被创建的时候，GLSurfaceView会调用这个方法，
         * 这发生在应用程序创建的第一次，并且当设备被唤醒或者用户从其他activity切换回去时，
         * 也会被调用
         *
         * @param gl
         * @param config
         */
        @Override
        public void onSurfaceCreated(GL10 gl, EGLConfig config) {
            GLES20.glClearColor(1.0f, 0.0f, 0.0f, 0.0f);
            mTriangle = new Triangle(mContext);
        }

        /**
         * 在Surface创建以后，每次Surface尺寸变化后，这个方法都会调用
         *
         * @param gl
         * @param width
         * @param height
         */
        @Override
        public void onSurfaceChanged(GL10 gl, int width, int height) {
            GLES20.glViewport(0, 0, width, height);
        }

        /**
         * 当绘制每一帧的时候会被调用
         *
         * @param gl
         */
        @Override
        public void onDrawFrame(GL10 gl) {
            GLES20.glClear(GL_COLOR_BUFFER_BIT);
            mTriangle.draw();

        }
    }

    private boolean supportOpenGl() {
        ActivityManager activityManager =
                (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
        ConfigurationInfo configurationInfo = activityManager
                .getDeviceConfigurationInfo();

        final boolean supportsEs2 =
                configurationInfo.reqGlEsVersion >= 0x20000
                        || (Build.VERSION.SDK_INT >= Build.VERSION_CODES.ICE_CREAM_SANDWICH_MR1
                        && (Build.FINGERPRINT.startsWith("generic")
                        || Build.FINGERPRINT.startsWith("unknown")
                        || Build.MODEL.contains("google_sdk")
                        || Build.MODEL.contains("Emulator")
                        || Build.MODEL.contains("Android SDK built for x86")));

        if (supportsEs2) {
            return true;
        } else {
            Toast.makeText(this, "This device does not support OpenGL ES 2.0.",
                    Toast.LENGTH_LONG).show();
            return false;
        }
    }
}
