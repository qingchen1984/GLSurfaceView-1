package com.wei.OpenGLES;

import android.opengl.GLU;

import java.util.ArrayList;
import java.util.List;

import javax.microedition.khronos.opengles.GL10;

/**
 * 三角形带渲染器
 * Created by 清才 on 2015/11/12.
 */
public class MyTriangleStripRenderer extends AbstractMyRenderer{

    /**
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
        float[] coords = {
            -r,r,0,      -r,-r,0,     r,r,0,    r,-r,0
        };
        //指定顶点数组
        gl.glVertexPointer(3, GL10.GL_FLOAT, 0, BufferUtil.arr2ByteBuffer(coords));
        // 绘制图形
        gl.glDrawArrays(GL10.GL_TRIANGLE_STRIP,0,coords.length/3);

    }
}
