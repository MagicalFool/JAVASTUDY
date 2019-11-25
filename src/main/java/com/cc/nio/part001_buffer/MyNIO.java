package com.cc.nio.part001_buffer;

import java.nio.Buffer;
import java.nio.IntBuffer;

public class MyNIO {
    static IntBuffer buffer = null;

    public static void main(String[] args) {
        // 创建intbuffer 缓冲区
        buffer = IntBuffer.allocate(20);
        display(buffer);
        // 写入值
        for (int i = 0; i< 5; i++){
            buffer.put(i);
        }
        display(buffer);
        // 变成读模式
        buffer.flip();
        int i1 = buffer.get();
        int i3 = buffer.get(3);
        System.out.println(i1 +","+i3);
        display(buffer);

    }

    public static void display(Buffer buffer){
        System.out.println("position" + buffer.position());
        System.out.println("capacity" + buffer.capacity());
        System.out.println("limit" + buffer.limit());
        System.out.println("mark" + buffer.mark().toString());
    }

}
