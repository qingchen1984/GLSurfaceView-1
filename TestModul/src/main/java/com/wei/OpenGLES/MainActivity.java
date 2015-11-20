package com.wei.OpenGLES;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;

/**
 * Created by 清才 on 2015/11/12.
 */
public class MainActivity extends Activity {
    float downX, downY, moveX, moveY;
    private AbstractMyRenderer renderer;
    private MyGLSurfaceView glSurfaceView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        glSurfaceView = new MyGLSurfaceView(this);
        //初始化渲染器
        renderer = new MyTriangleConeRenderer();
        //设置渲染器
        glSurfaceView.setRenderer(renderer);
        setContentView(glSurfaceView);

    }

    float angle = 0;

    @Override
    public boolean onTouchEvent(final MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                downX = event.getX();
                downY = event.getY();
                renderer.xrotate = 45;

                angle++;
                Log.i("onTouchEvent-------DOWN", downX + "\t" + downY);
                break;
            case MotionEvent.ACTION_UP:



                Log.i("onTouchEvent------UP", moveX + "\t" + moveY);
                break;
        }
        if (moveX > 0) {

        }
        return true;
    }

}
