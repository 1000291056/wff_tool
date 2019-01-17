package com.wff.androidtool.ui.view;

import android.content.Context;
import android.graphics.Canvas;
import android.hardware.Camera;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.io.IOException;

public class CameraView extends SurfaceView implements SurfaceHolder.Callback {
    private SurfaceHolder mSurfaceHolder;
    private Camera mCamera;

    void init() {
        mSurfaceHolder = getHolder();
        mSurfaceHolder.addCallback(this);
    }

    public CameraView(Context context) {
        super(context);
    }

    public CameraView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CameraView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }

    public void setCamera(Camera camera) {
        this.mCamera = camera;
//        mCamera.setPreviewDisplay();
        mCamera.startPreview();
    }

    void setAndStartPreviewDisplay(SurfaceHolder holder) {
        checkCamera();
        try {
            mCamera.setPreviewDisplay(holder);
            mCamera.startPreview();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    void stopPreviewDisplay() {
        checkCamera();
        mCamera.stopPreview();
    }

    void setDisplayOrientation(int degrees) {
        checkCamera();
        mCamera.setDisplayOrientation(degrees);
    }

    void checkCamera() {
        if (mCamera == null) {
            throw new NullPointerException("未设置camera！");
        }
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        setAndStartPreviewDisplay(holder);
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
        if (holder.getSurface() == null) {
            return;
        }
//        Cameras.followScreenOrientation(getContext(), mCamera);
        stopPreviewDisplay();
        setAndStartPreviewDisplay(holder);


    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        stopPreviewDisplay();
    }

    public void capturePicture() {
        checkCamera();
        mCamera.takePicture(new Camera.ShutterCallback() {
            @Override
            public void onShutter() {

            }
        }, new Camera.PictureCallback() {
            @Override
            public void onPictureTaken(byte[] data, Camera camera) {

            }
        }, new Camera.PictureCallback() {
            @Override
            public void onPictureTaken(byte[] data, Camera camera) {

            }
        });
    }
}
