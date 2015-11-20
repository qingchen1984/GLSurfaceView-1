package com.wei.OpenGLES;

import android.opengl.GLSurfaceView;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

/**
 * Created by 清才 on 2015/11/9.
 */
public abstract class AbstractMyRenderer implements GLSurfaceView.Renderer {
    public float xLook = 0;
    public float yLook = 0;
    public float zLook = 5;
    public float xrotate;
    public float yrotate;
    public float zrotate;
    public float ratio;
    /**
     * 1>
     *
     * @param gl
     * @param config
     */
    @Override
    public void onSurfaceCreated(GL10 gl, EGLConfig config) {
        //设置清屏色
        gl.glClearColor(0f, 0f, 0f, 1f);
        //启用顶点缓冲区
        gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);

    }

    /**
     * 2>
     *
     * @param gl
     * @param width
     * @param height
     */
    @Override
    public void onSurfaceChanged(GL10 gl, int width, int height) {
        //设置视口
        gl.glViewport(0, 0, width, height);
        ratio = (float) width / height;
        //启用投影矩阵
        gl.glMatrixMode(GL10.GL_PROJECTION);
        gl.glLoadIdentity();
        //设置平截头体
        gl.glFrustumf(-ratio, ratio, -1f, 1f, 3f, 7f);
    }
}
