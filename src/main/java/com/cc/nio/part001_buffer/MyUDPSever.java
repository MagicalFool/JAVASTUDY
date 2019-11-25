package com.cc.nio.part001_buffer;


import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.util.Iterator;

public class MyUDPSever {

    DatagramChannel datagramChannel;

    public static void main(String[] args) {
        new MyUDPSever().server();
    }

    public void server(){
        try {
            datagramChannel = DatagramChannel.open();
            // 设置为非阻塞模式
            datagramChannel.configureBlocking(false);
            datagramChannel.bind(new InetSocketAddress("127.0.0.1",1180));
            System.out.println("socket start");
            Selector selector = Selector.open();
            datagramChannel.register(selector, SelectionKey.OP_READ);
            while (selector.select() > 0){
                Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
                ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
                while (iterator.hasNext()){
                    SelectionKey next = iterator.next();
                    if (next.isReadable()){
                        datagramChannel.receive(byteBuffer);
                        byteBuffer.flip();
                        System.out.println(new String(byteBuffer.array(),0,byteBuffer.limit()));
                        byteBuffer.clear();
                    }
                }
                iterator.remove();

            }
            selector.close();
            datagramChannel.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
