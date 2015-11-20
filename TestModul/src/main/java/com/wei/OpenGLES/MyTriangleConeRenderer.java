package com.wei.OpenGLES;

import android.opengl.GLU;

import java.nio.ByteBuffer;
import java.nio.FloatBuffer;
import java.util.ArrayList;
import java.util.List;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

/**
 * 棱锥渲染器
 * Created by 清才 on 2015/11/12.
 */
public class MyTriangleConeRenderer extends AbstractMyRenderer {


    @Override
    public void onSurfaceCreated(GL10 gl, EGLConfig config) {
        super.onSurfaceCreated(gl, config);
        //启用颜色缓冲区
        gl.glEnableClientState(GL10.GL_COLOR_ARRAY);
    }

    /**
     * @param gl
     */
    @Override
    public void onDrawFrame(GL10 gl) {
        //清除颜色缓冲区
        gl.glClear(GL10.GL_COLOR_BUFFER_BIT | GL10.GL_DEPTH_BITS);
        //设置绘图颜色
        gl.glColor4f(1f, 0f, 0f, 1f);
        //启用深度测试
        gl.glEnableClientState(GL10.GL_DEPTH_TEST);
        gl.glEnable(GL10.GL_CULL_FACE);
        gl.glFrontFace(GL10.GL_CCW);
        gl.glCullFace(GL10.GL_BACK);

        //设置绘图方式为单调模式
        gl.glShadeModel(GL10.GL_FLAT);
        //操作模型视图矩阵
        gl.glMatrixMode(GL10.GL_MODELVIEW);
        gl.glLoadIdentity();
        //设置眼球的参数
        GLU.gluLookAt(gl, xLook, yLook, zLook, 0f, 0f, 0f, 0f, 1f, 0f);
        //旋转角度
        gl.glRotatef(xrotate, 1, 0, 0);
        gl.glRotatef(yrotate, 0, 1, 0);
        gl.glRotatef(zrotate, 0, 0, 1);
        //计算点的坐标
        float r = 0.5f;//半径
        float x = 0f, y = 0f, z = -0.5f;
        List<Float> coordsList = new ArrayList<Float>();
        //添加锥顶点
        coordsList.add(0f);
        coordsList.add(0f);
        coordsList.add(0.5f);
        //颜色集合
        List<Float> colorList = new ArrayList<Float>();
        colorList.add(1f);//r
        colorList.add(0f);//g
        colorList.add(0f);//b
        colorList.add(1f);//a

        //锥底面
        List<Float> coordsConeBottomList = new ArrayList<Float>();
        coordsConeBottomList.add(0f);
        coordsConeBottomList.add(0f);
        coordsConeBottomList.add(-0.5f);
        //颜色标志
        boolean flag = false;

        //添加锥底面
        for (float alpha = 0f; alpha < Math.PI * 2.125; alpha = (float) (alpha + Math.PI / 8)) {
            //地面圆周各点坐标值
            x = (float) (Math.cos(alpha) * r);
            y = (float) (Math.sin(alpha) * r);
            //给锥面添加坐标
            coordsList.add(x);
            coordsList.add(y);
            coordsList.add(z);
            //给锥底添加坐标
            coordsConeBottomList.add(x);
            coordsConeBottomList.add(y);
            coordsConeBottomList.add(z);

            //底面的颜色值
            if (flag = !flag) {
                //添加黄色
                colorList.add(1f);
                colorList.add(1f);
                colorList.add(0f);
                colorList.add(1f);

            } else {
                //添加红色
                colorList.add(1f);
                colorList.add(0f);
                colorList.add(0f);
                colorList.add(1f);
            }
        }
        FloatBuffer colorBuffer = BufferUtil.list2FloatBuffer(colorList);
        // 绘制锥面
        gl.glColorPointer(4, GL10.GL_FLOAT, 0, colorBuffer);
        gl.glVertexPointer(3, GL10.GL_FLOAT, 0, BufferUtil.list2ByteBuffer(coordsList));
        gl.glDrawArrays(GL10.GL_TRIANGLE_FAN, 0, coordsList.size() / 3);
        //设置在绘制底面的时候剔除正面
        gl.glCullFace(GL10.GL_FRONT);
        //绘制锥底
        colorBuffer.position(4);
        gl.glColorPointer(4, GL10.GL_FLOAT, 0, colorBuffer);
        gl.glVertexPointer(3, GL10.GL_FLOAT, 0, BufferUtil.list2ByteBuffer(coordsConeBottomList));
        gl.glDrawArrays(GL10.GL_TRIANGLE_FAN, 0, coordsConeBottomList.size() / 3);
    }
}
