package com.cc.nio.part001_buffer;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SocketChannel;

public class MySocketChannel {

    public static void main(String[] args) {
        SocketChannel sc = null;

        try {
            sc = SocketChannel.open();
            sc.configureBlocking(false);
            sc.connect(new InetSocketAddress("127.0.0.1",1180));
            while (!sc.finishConnect()){

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
