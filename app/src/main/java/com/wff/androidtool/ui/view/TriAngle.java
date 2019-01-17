package com.wff.androidtool.ui.view;

import android.opengl.GLES20;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

/**
 * Created by wufeifei on 2017/3/27.
 */

public class TriAngle {
    private final String vertexShaderCode =
            "attribute vec4 vPosition;" +
                    "void main() {" +
                    "  gl_Position = vPosition;" +
                    "}";

    private final String fragmentShaderCode =
            "precision mediump float;" +
                    "uniform vec4 vColor;" +
                    "void main() {" +
                    "  gl_FragColor = vColor;" +
                    "}";
    private FloatBuffer mFloatBuffer;
    float[] points = {//顶点
            0f, 0f, 1f,
            1f, 0f, 0f,
            0f, 1f, 0f
    };
    // 设置三角形颜色和透明度（r,g,b,a）
    float color[] = {0.0f, 1.0f, 0f, 1.0f};//绿色不透明
    private final int mProgram;

    public static int loadShader(int type, String shaderCode) {

        //创建一个vertex shader类型(GLES20.GL_VERTEX_SHADER)
        //或一个fragment shader类型(GLES20.GL_FRAGMENT_SHADER)
        int shader = GLES20.glCreateShader(type);

        // 将源码添加到shader并编译它
        GLES20.glShaderSource(shader, shaderCode);
        GLES20.glCompileShader(shader);

        return shader;
    }

    public TriAngle() {
        ByteBuffer byteBuffer = ByteBuffer.allocate(points.length * 4);//每个float占4个字节
        byteBuffer.order(ByteOrder.nativeOrder());
        mFloatBuffer = byteBuffer.asFloatBuffer();
        mFloatBuffer.put(points);
        mFloatBuffer.position(0);
        // 编译shader代码
        int vertexShader = loadShader(GLES20.GL_VERTEX_SHADER,
                vertexShaderCode);
        int fragmentShader = loadShader(GLES20.GL_FRAGMENT_SHADER,
                fragmentShaderCode);

        // 创建空的OpenGL ES Program
        mProgram = GLES20.glCreateProgram();

        // 将vertex shader添加到program
        GLES20.glAttachShader(mProgram, vertexShader);

        // 将fragment shader添加到program
        GLES20.glAttachShader(mProgram, fragmentShader);

        // 创建可执行的 OpenGL ES program
        GLES20.glLinkProgram(mProgram);

    }
    private int mPositionHandle;
    private int mColorHandle;
    //设置每个顶点的坐标数
    static final int COORDS_PER_VERTEX = 3;
    private final int vertexCount = points.length / COORDS_PER_VERTEX;
    private final int vertexStride = COORDS_PER_VERTEX * 4; // 4 bytes per vertex

    public void draw() {
        // 添加program到OpenGL ES环境中
        GLES20.glUseProgram(mProgram);

        // 获取指向vertex shader的成员vPosition的handle
        mPositionHandle = GLES20.glGetAttribLocation(mProgram, "vPosition");

        // 启用一个指向三角形的顶点数组的handle
        GLES20.glEnableVertexAttribArray(mPositionHandle);

        //准备三角形的坐标数据
        GLES20.glVertexAttribPointer(mPositionHandle, COORDS_PER_VERTEX,
                GLES20.GL_FLOAT, false,
                vertexStride, mFloatBuffer);

        // 获取指向fragment shader的成员vColor的handle
        mColorHandle = GLES20.glGetUniformLocation(mProgram, "vColor");

        //  绘制三角形
        GLES20.glUniform4fv(mColorHandle, 1, color, 0);

        // Draw the triangle
        GLES20.glDrawArrays(GLES20.GL_TRIANGLES, 0, vertexCount);

        // 禁用指向三角形的顶点数组
        GLES20.glDisableVertexAttribArray(mPositionHandle);
    }
}
