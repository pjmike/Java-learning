package com.pjmike.nio2;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousServerSocketChannel;
import java.nio.channels.AsynchronousSocketChannel;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * @author pjmike
 * @create 2018-08-29 19:40
 */
public class AsyncEchoServer {
    private AsynchronousServerSocketChannel server;
    private Future<AsynchronousSocketChannel> future;
    private AsynchronousSocketChannel worker;

    public AsyncEchoServer() throws IOException, ExecutionException, InterruptedException {
        System.out.println("Open Server Channel");
        server = AsynchronousServerSocketChannel.open().bind(new InetSocketAddress("127.0.0.1", 9090));
        future = server.accept();
    }

    public void runServer() throws ExecutionException, InterruptedException, IOException, TimeoutException {
        //获取操作结果
        worker = future.get();
        if (worker != null && worker.isOpen()) {
            ByteBuffer buffer = ByteBuffer.allocate(100);
            //将通道中的数据写入缓冲区
            worker.read(buffer).get(10,TimeUnit.SECONDS);
            System.out.println("received from client: " + new String(buffer.array()));
        }
        server.close();
    }

    public static void main(String[] args) throws InterruptedException, ExecutionException, IOException, TimeoutException {
        AsyncEchoServer server = new AsyncEchoServer();
        server.runServer();
    }
}
