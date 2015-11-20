package com.wei.OpenGLES;

import android.opengl.GLU;

import javax.microedition.khronos.opengles.GL10;

/**
 * 裁剪渲染器
 * Created by 清才 on 2015/11/12.
 */
public class MyScissorRenderer extends AbstractMyRenderer {
    private int width;
    private int height;

    @Override
    public void onSurfaceChanged(GL10 gl, int width, int height) {
        super.onSurfaceChanged(gl, width, height);

        this.width = width;
        this.height = height;
    }

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
        //启用剪裁测试
        gl.glEnable(GL10.GL_SCISSOR_TEST);

        //旋转角度
        gl.glRotatef(xrotate, 1, 0, 0);
        gl.glRotatef(yrotate, 0, 1, 0);
        //计算点的坐标
        float[] coords = {
                -ratio, 1f, 2f,
                -ratio, -1f, 2f,
                ratio, 1f, 2f,
                ratio, -1f, 2f,
        };
        //颜色数组
        float[][] colors = {
                {1, 0, 0, 1},
                {0, 1, 0, 1},
                {0, 0, 1, 1},
                {1, 1, 0, 1},
                {0, 1, 1, 1},
                {1, 0, 1, 1},
        };
        int step = 20;
        for (int i = 0; i < 6; i++) {
            //设置剪裁区域
            gl.glScissor(i * 20, i * 20, width - i * 20 * 2, height - i * 20 * 2);
            gl.glColor4f(colors[i][0], colors[i][1], colors[i][2], colors[i][3]);
            gl.glVertexPointer(3, GL10.GL_FLOAT, 0, BufferUtil.arr2ByteBuffer(coords));
            gl.glDrawArrays(GL10.GL_TRIANGLE_STRIP, 0, coords.length / 3);

        }
    }
}
