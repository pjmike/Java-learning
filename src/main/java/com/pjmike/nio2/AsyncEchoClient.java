package com.pjmike.nio2;

import sun.awt.image.IntegerComponentRaster;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousSocketChannel;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * @author pjmike
 * @create 2018-08-29 20:00
 */
public class AsyncEchoClient {
    private AsynchronousSocketChannel client;
    private Future<Void> future;

    public AsyncEchoClient() throws IOException {
        //打开一个异步channel
        System.out.println("Open client channel");
        client = AsynchronousSocketChannel.open();
        //连接本地端口和地址,在连接成功后不返回任何内容，但是，我们仍然可以使用Future对象来监视异步操作的状态
        System.out.println("Connect to server");
        future = client.connect(new InetSocketAddress("127.0.0.1", 9090));
    }

    /**
     * 向服务端发送消息
     *
     * @param message
     * @return
     */
    public void sendMessage(String message) throws ExecutionException, InterruptedException {
        if (!future.isDone()) {
            future.cancel(true);
            return;
        }
        //将一个字节数组封装到ByteBuffer中
        ByteBuffer byteBuffer = ByteBuffer.wrap("hi".getBytes());
        System.out.println("Sending message to the server");
        //将数据写入通道
        int numberBytes = client.write(byteBuffer).get();
        byteBuffer.clear();
    }

    public static void main(String[] args) throws IOException, ExecutionException, InterruptedException {
        AsyncEchoClient client = new AsyncEchoClient();
        client.sendMessage("hello world");
    }
}
