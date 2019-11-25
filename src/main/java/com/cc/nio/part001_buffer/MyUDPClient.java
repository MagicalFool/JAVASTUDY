package com.cc.nio.part001_buffer;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;
import java.util.Date;
import java.util.Scanner;

public class MyUDPClient {

    DatagramChannel datagramChannel;

    public static void main(String[] args) {
        new MyUDPClient();
    }

    {
        try {
            datagramChannel = DatagramChannel.open();
            datagramChannel.configureBlocking(false);
            ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
            Scanner scanner = new Scanner(System.in);

            while (scanner.hasNext()){
                String next = scanner.next();
                byteBuffer.put((new Date() + ">>" + next ).getBytes());
                byteBuffer.flip();
                datagramChannel.send(byteBuffer, new InetSocketAddress("127.0.0.1",1180) );
                byteBuffer.clear();
            }
            datagramChannel.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
