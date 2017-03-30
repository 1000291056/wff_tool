package com.wff.wff_tool.ui.view;

import android.content.Context;
import android.opengl.GLSurfaceView;
import android.util.AttributeSet;

/**
 * Created by wufeifei on 2016/12/21.
 */

public class MyGLSurfaceView extends GLSurfaceView {

    private final MyRender mRenderer;

    public MyGLSurfaceView(Context context){
        super(context);

        // 创建OpenGL ES 2.0的上下文
        setEGLContextClientVersion(2);

        mRenderer = new MyRender();

        //设置Renderer用于绘图
        setRenderer(mRenderer);

        //只有在绘制数据改变时才绘制view，可以防止GLSurfaceView帧重绘
        setRenderMode(GLSurfaceView.RENDERMODE_WHEN_DIRTY);
    }
}