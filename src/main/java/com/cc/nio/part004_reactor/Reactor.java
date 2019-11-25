package com.cc.nio.part004_reactor;

import java.io.IOException;
import java.nio.channels.*;
import java.util.Iterator;
import java.util.Set;

public class Reactor implements Runnable {
    Selector selector;
    ServerSocketChannel serverSocketChannel;


    public Reactor() throws ClosedChannelException {
        SelectionKey sk = serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
        sk.attach(new AcceptorHandler());
    }

    @Override
    public void run() {
        try {
            while (!Thread.interrupted()) {

                selector.select();
                Set<SelectionKey> selectionKeys = selector.selectedKeys();
                Iterator<SelectionKey> iterator = selectionKeys.iterator();
                while (iterator.hasNext()){
                    SelectionKey sk = iterator.next();
                    dispatch(sk);
                }
                selectionKeys.clear();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    void dispatch(SelectionKey sk){
        Runnable runnable = (Runnable) sk.attachment();
        if (runnable != null){
            runnable.run();
        }
    }

    class AcceptorHandler implements Runnable{

        @Override
        public void run() {
            try {
                SocketChannel channel = serverSocketChannel.accept();
//                new
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        try {
            new Thread(new Reactor()).start();
        } catch (ClosedChannelException e) {
            e.printStackTrace();
        }
    }
}
