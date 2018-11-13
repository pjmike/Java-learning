package com.pjmike.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

/**
 * @author pjmike
 * @create 2018-09-14 22:48
 */
public class ServerSocketChannelDemo {
    private ServerSocketChannel serverSocketChannel;
    private Selector selector;

    public ServerSocketChannelDemo(int port) throws IOException {
        serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.configureBlocking(false);
        serverSocketChannel.socket().bind(new InetSocketAddress(port));
        selector = Selector.open();
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
    }

    public void listener() throws IOException {
        while (true) {
            int n = selector.select();
            if (n == 0) {
                continue;
            }
            Iterator iterator = selector.selectedKeys().iterator();
            while (iterator.hasNext()) {
                SelectionKey selectionKey = (SelectionKey) iterator.next();
                if (selectionKey.isAcceptable()) {
                    ServerSocketChannel server_channel = (ServerSocketChannel) selectionKey.channel();
                    SocketChannel socketChannel = server_channel.accept();
                    socketChannel.configureBlocking(false);
                    socketChannel.register(selector, SelectionKey.OP_READ);
                }
                if (selectionKey.isReadable()) {
                    //如果通道处于读就绪的状态
                    //读操作
                    //TODO
                }
            }
        }
    }
}
