package com.wff.androidtool.opengles;

import android.content.Context;
import android.opengl.GLES20;

import com.wff.androidtool.R;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

/**
 * @author ffw
 */
public class Triangle {

    /**
     * 坐标本地内存地址
     */
    private FloatBuffer vertexBuffer;

    /**
     * 每一次取点的时候取几个点
     */
    static final int COORDS_PER_VERTEX = 3;

    /**
     * 绘制坐标
     */

    static float[] triAngleCoords = {
            // top
            0.0f, 0.622008459f, 0.0f,
            // bottom left
            -0.5f, -0.311004243f, 0.0f,
            // bottom right
            0.5f, -0.311004243f, 0.0f
    };

    /**
     * Set color with red, green, blue and alpha (opacity) values
     */
    float[] color = {0.63671875f, 0.76953125f, 0.22265625f, 1.0f};


    private final int mProgram;

    private int mPositionHandle;
    private int mColorHandle;

    private final int vertexCount = triAngleCoords.length / COORDS_PER_VERTEX;
    /**
     * 每一次取的总的点 大小
     * 4 bytes per vertex
     */
    private final int vertexStride = COORDS_PER_VERTEX * 4;

    public Triangle(Context context) {
        //为坐标分配本地内存地址
        vertexBuffer = ByteBuffer
                .allocateDirect(triAngleCoords.length * 4)
                .order(ByteOrder.nativeOrder())
                .asFloatBuffer()
                .put(triAngleCoords);
        vertexBuffer.position(0);

        //根据shader代码和fragment代码 获取到一个渲染程序
        mProgram = ShaderUtil.createProgram(ShaderUtil.readRawTxt(context, R.raw.vertex_shader),
                ShaderUtil.readRawTxt(context, R.raw.fragment_shader));
        if (mProgram > 0) {
            //获取vertex shader的属性vPosition 的地址
            mPositionHandle = GLES20.glGetAttribLocation(mProgram, "vPosition");
            //获取fragment shader的属性vColor 的地址
            mColorHandle = GLES20.glGetUniformLocation(mProgram, "vColor");
        }
    }


    public void draw() {
        //使用渲染程序
        GLES20.glUseProgram(mProgram);

        // 使顶点属性数组有效
        GLES20.glEnableVertexAttribArray(mPositionHandle);

        // 为顶点属性赋值
        GLES20.glVertexAttribPointer(mPositionHandle, COORDS_PER_VERTEX,
                GLES20.GL_FLOAT, false,
                vertexStride, vertexBuffer);

        // 设置颜色
        GLES20.glUniform4fv(mColorHandle, 1, color, 0);

        // 绘制图形
        GLES20.glDrawArrays(GLES20.GL_TRIANGLES, 0, vertexCount);

        // 禁用顶点数组
        GLES20.glDisableVertexAttribArray(mPositionHandle);

    }
}