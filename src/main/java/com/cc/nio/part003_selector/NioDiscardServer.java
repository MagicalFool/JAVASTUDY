package com.cc.nio.part003_selector;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Iterator;

public class NioDiscardServer {
    public static void  startServer() throws IOException{
        // 1. 获取选择器
        Selector selector = Selector.open();
        // 2. 获取通道
        ServerSocketChannel socketChannel = ServerSocketChannel.open();
        // 3. 设置为非阻塞
        socketChannel.configureBlocking(false);
        // 4. 绑定链接
        socketChannel.bind(new InetSocketAddress("127.0.0.1",10019));
        
        socketChannel.register(selector, SelectionKey.OP_ACCEPT);
        
        while (selector.select() > 0){
            Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
            while (iterator.hasNext()){
                SelectionKey next = iterator.next();
                if (next.isAcceptable()) {
                    SocketChannel accept = socketChannel.accept();
                    accept.configureBlocking(false);
                    accept.register(selector,SelectionKey.OP_READ);
                }else if (next.isReadable()){
                    SocketChannel channel = (SocketChannel)next.channel();
                    ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
                    int length = 0;
                    while ((length = channel.read(byteBuffer)) > 0){
                        byteBuffer.flip();
                        System.out.println(new String(byteBuffer.array(),0,length));
                        byteBuffer.clear();
                    }
                    socketChannel.close();
                }
                iterator.remove();
            }
        }
        socketChannel.close();

    }

    public static void main(String[] args) throws IOException {
        startServer();
    }
}
