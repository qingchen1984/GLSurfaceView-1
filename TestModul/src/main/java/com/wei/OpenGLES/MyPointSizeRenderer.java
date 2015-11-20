package com.wei.OpenGLES;

import android.opengl.GLSurfaceView;
import android.opengl.GLU;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.util.ArrayList;
import java.util.List;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

/**
 * 点渲染器
 * Created by 清才 on 2015/11/12.
 */
public class MyPointSizeRenderer extends AbstractMyRenderer{

    /**
     * 3>
     *
     * @param gl
     */
    @Override
    public void onDrawFrame(GL10 gl) {
        //清除颜色缓冲区
        gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
        //设置绘图颜色
        gl.glColor4f(1f, 0f, 0f, 1f);
        //操作模型视图矩阵
        gl.glMatrixMode(GL10.GL_MODELVIEW);
        gl.glLoadIdentity();
        //设置眼球的参数
        GLU.gluLookAt(gl, 0f, 0f, 5f, 0f, 0f, 0f, 0f, 1f, 0f);
        //旋转角度
        gl.glRotatef(xrotate,1,0,0);
        gl.glRotatef(yrotate,0,1,0);
        //计算点的坐标
        float r = 0.5f;//半径
        float zStep = 0.01f;
        float x = 0f, y = 0f, z = 1.0f;
        float pSize = 1.0f;
        float pStep = 0.5f;
        //循环绘制点
        for (float alpha = 0f; alpha < Math.PI * 6; alpha = (float) (alpha + Math.PI / 16)) {
            x = (float) (Math.cos(alpha) * r);
            y = (float) (Math.sin(alpha) * r);
            z = (z - zStep);
            gl.glPointSize(pSize = pSize + pStep);
            //指定顶点的指针
            gl.glVertexPointer(3, GL10.GL_FLOAT, 0, BufferUtil.arr2ByteBuffer(new float[]{x,y,z}));
            gl.glDrawArrays(GL10.GL_POINTS, 0, 1);
        }

    }
}
