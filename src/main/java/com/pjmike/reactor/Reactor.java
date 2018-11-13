package com.pjmike.reactor;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.*;
import java.util.Iterator;
import java.util.Set;


/**
 * 反应器
 *
 * @author pjmike
 * @create 2018-09-06 22:48
 */
public class Reactor implements Runnable {
    final Selector selector;
    final ServerSocketChannel serverSocketChannel;

    Reactor(int port) throws IOException {
        selector = Selector.open();
        serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.socket().bind(new InetSocketAddress(port));
        //设置为非阻塞
        serverSocketChannel.configureBlocking(false);
        SelectionKey selectionKey = serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
        //将 Acceptor对象附在 SelectionKey上，处理新连接
        selectionKey.attach(new Acceptor());
    }

    @Override
    public void run() {

        try {
            while (!Thread.interrupted()) {
                //监听事件
                selector.select();
                Set<SelectionKey> selected = selector.selectedKeys();
                Iterator iterator = selected.iterator();
                while (iterator.hasNext()) {
                    iterator.remove();
                    //分发事件处理
                    dispatch((SelectionKey) iterator.next());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void dispatch(SelectionKey key) {
        //若是连接事件获取是acceptor
        //若是IO读写事件获取是 handler
        Runnable runnable = (Runnable) key.attachment();
        if (runnable != null) {
            runnable.run();
        }
    }

    class Acceptor implements Runnable {

        @Override
        public void run() {
            try {
                SocketChannel socketChannel = serverSocketChannel.accept();
                if (socketChannel != null) {
                    //注册读写
                    new Handler(selector, socketChannel);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 处理业务逻辑
     */
    final class Handler implements Runnable {
        public static final int READING = 0, WRITING = 1;
        int state;
        final SocketChannel socketChannel;
        final SelectionKey selectionKey;

        Handler(Selector selector, SocketChannel socketChannel) throws IOException {
            this.state = READING;
            this.socketChannel = socketChannel;
            selectionKey = socketChannel.register(selector, SelectionKey.OP_READ);
            selectionKey.attach(this);
            socketChannel.configureBlocking(false);
            //让阻塞在 select()方法上的线程立马返回
            selector.wakeup();
        }

        @Override
        public void run() {
            if (state == READING) {
                read();
            } else if (state == WRITING) {
                write();
            }
        }

        private void write() {
            process();
            //下一步处理读事件
            selectionKey.interestOps(SelectionKey.OP_READ);
            this.state = READING;
        }

        private void read() {
            process();
            //下一步处理写事件
            selectionKey.interestOps(SelectionKey.OP_WRITE);
            this.state = WRITING;
        }

        private void process() {
            //do something
        }
    }
}

