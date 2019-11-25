package com.cc.nio.part001_buffer;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class MyFileChannel {

    public static void main(String[] args) {
        try {
            // 输入流
            File f = new File("/home/chenchao/IdeaProjects/me/JAVASTUDAY/src/main/java/com/cc/hc/nio/hello.txt");
            File f2 = new File("/home/chenchao/IdeaProjects/me/JAVASTUDAY/src/main/java/com/cc/hc/nio/temp.txt");
            FileInputStream is = new FileInputStream(f);
            FileChannel channel = is.getChannel();

            // 输出流
            FileOutputStream os = new FileOutputStream(f2);
            FileChannel osChannel = os.getChannel();

            ByteBuffer bf = ByteBuffer.allocate(1024);
            int len = -1;

            while ((len = channel.read(bf)) != -1){
                bf.flip();
                int outlen= 0;
                while ((outlen = osChannel.write(bf)) != 0){
                    System.out.println(outlen);
                }
                bf.clear();
            }



        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {

        }
    }
}
