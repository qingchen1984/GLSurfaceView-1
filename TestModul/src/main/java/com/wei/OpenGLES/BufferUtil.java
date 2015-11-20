package com.wei.OpenGLES;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.util.List;

/**
 * Created by 清才 on 2015/11/13.
 */
public class BufferUtil {
    /**
     * 将浮点数组返回字节缓冲区
     * @return
     */
    public static ByteBuffer arr2ByteBuffer(float[] arr) {
        //将点的坐标装换成缓冲区
        ByteBuffer byteBuffer = ByteBuffer.allocateDirect(arr.length * 4);
        byteBuffer.order(ByteOrder.nativeOrder());
        FloatBuffer floatBuffer = byteBuffer.asFloatBuffer();
        floatBuffer.put(arr);
        byteBuffer.position(0);
        return byteBuffer;
    };

    /**
     * 将浮点数集合返回字节缓冲区
     * @return
     */
    public static ByteBuffer list2ByteBuffer(List<Float> list) {
        //将点的坐标装换成缓冲区
        ByteBuffer byteBuffer = ByteBuffer.allocateDirect(list.size() * 4);
        byteBuffer.order(ByteOrder.nativeOrder());
        FloatBuffer floatBuffer = byteBuffer.asFloatBuffer();
        for (float f : list) {
            floatBuffer.put(f);
        }
        byteBuffer.position(0);
        return byteBuffer;
    };

    /**
     * 将浮点数集合返回浮点数缓冲区
     * @return
     */
    public static FloatBuffer list2FloatBuffer(List<Float> list) {
        //将点的坐标装换成缓冲区
        ByteBuffer byteBuffer = ByteBuffer.allocateDirect(list.size() * 4);
        byteBuffer.order(ByteOrder.nativeOrder());
        FloatBuffer floatBuffer = byteBuffer.asFloatBuffer();
        for (Float f : list) {
            floatBuffer.put(f);
        }
        floatBuffer.position(0);
        return floatBuffer;
    };
}
