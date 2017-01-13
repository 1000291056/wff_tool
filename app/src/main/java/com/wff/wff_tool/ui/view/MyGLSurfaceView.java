package com.wff.wff_tool.ui.view;

import android.content.Context;
import android.opengl.GLSurfaceView;
import android.util.AttributeSet;

/**
 * Created by wufeifei on 2016/12/21.
 */

public class MyGLSurfaceView extends GLSurfaceView {
    public MyGLSurfaceView(Context context) {
        super(context);
    }

    public MyGLSurfaceView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    private void init(Context context) {
        // Set the Renderer for drawing on the GLSurfaceView
        setRenderer(new MyGL20Renderer());
    }
}
