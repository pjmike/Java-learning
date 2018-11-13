package com.pjmike.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

/**
 * 服务端
 *
 * @author pjmike
 * @create 2018-08-29 9:49
 */
public class ServerSocketChannelTest {
    private int size = 1024;
    private ServerSocketChannel serverSocketChannel;
    private ByteBuffer byteBuffer;
    private Selector selector;
    private final int port = 9999;
    private int remoteClientNum = 0;

    public ServerSocketChannelTest() {
        try {
            initChannel();
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(-1);
        }
    }

    public void initChannel() throws IOException {
        serverSocketChannel = ServerSocketChannel.open();
        //设置为非阻塞模式
        serverSocketChannel.configureBlocking(false);
        //绑定端口
        serverSocketChannel.bind(new InetSocketAddress(port));
        System.out.println("listener on port: " + port);
        //选择器的创建
        selector = Selector.open();
        //向选择器注册通道
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
        //分配缓冲区大小
        byteBuffer = ByteBuffer.allocate(size);
    }
    private void listener() throws Exception {
        while (true) {
            //返回的int值表示有多少通道就绪
            int n = selector.select();
            if (n == 0) {
                continue;
            }
            Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
            while (iterator.hasNext()) {
                SelectionKey key = iterator.next();
                //ServerSocketChannel 处于接收就绪状态
                if (key.isAcceptable()) {
                    ServerSocketChannel server = (ServerSocketChannel) key.channel();
                    SocketChannel channel = server.accept();
                    registerChannel(selector, channel, SelectionKey.OP_READ);
                    //远程客户端连接数
                    remoteClientNum++;
                    System.out.println("online client num="+remoteClientNum);
                    replyClient(channel);
                }
                //如果通道已经处于读就绪状态
                if (key.isReadable()) {
                    readDataFromSocket(key);
                }
                iterator.remove();
            }
        }
    }

    private void readDataFromSocket(SelectionKey key) throws IOException {
        SocketChannel socketChannel = (SocketChannel) key.channel();
        int count;
        byteBuffer.clear();
        //从通道中读数据到缓冲区
        while ((count = socketChannel.read(byteBuffer)) > 0) {
            //byteBuffer从上面的写模式变为读模式
            byteBuffer.flip();
            while (byteBuffer.hasRemaining()) {
                System.out.print((char)byteBuffer.get());
            }
            byteBuffer.clear();

        }
        if (count < 0) {
            socketChannel.close();
        }
    }

    private void replyClient(SocketChannel channel) throws IOException {
        byteBuffer.clear();
        byteBuffer.put("hello client!".getBytes());
        //byteBuffer从写模式变成读模式
        byteBuffer.flip();
        //将缓冲区的数据写入通道中
        channel.write(byteBuffer);
    }

    private void registerChannel(Selector selector, SocketChannel channel, int opRead) throws IOException {
        if (channel == null) {
            return;
        }
        channel.configureBlocking(false);
        channel.register(selector, opRead);
    }

    public static void main(String[] args) {
        try {
            new ServerSocketChannelTest().listener();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
