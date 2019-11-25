package com.cc.nio.part003_selector;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

public class NioDiscardClient {
    static void startClient() throws IOException{
        InetSocketAddress address = new InetSocketAddress("127.0.0.1",10019);
        SocketChannel socketChannel = SocketChannel.open(address);
        socketChannel.configureBlocking(false);
        while (!socketChannel.finishConnect()){

        }
        System.out.println("socket connected");
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
        byteBuffer.put("hello world".getBytes());
        byteBuffer.flip();
        socketChannel.write(byteBuffer);
        socketChannel.shutdownOutput();
        socketChannel.close();
    }

    public static void main(String[] args) throws IOException {
        startClient();
    }
}
