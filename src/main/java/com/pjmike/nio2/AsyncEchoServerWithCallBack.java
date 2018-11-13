package com.pjmike.nio2;


import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousChannelGroup;
import java.nio.channels.AsynchronousServerSocketChannel;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;
import java.util.concurrent.*;

/**
 * @author pjmike
 * @create 2018-08-29 19:40
 */
public class AsyncEchoServerWithCallBack {
    private AsynchronousServerSocketChannel server;
    private AsynchronousSocketChannel worker;
    private AsynchronousChannelGroup group;
    public AsyncEchoServerWithCallBack() throws IOException, ExecutionException, InterruptedException {
        System.out.println("Open Server Channel");
        group = AsynchronousChannelGroup.withFixedThreadPool(10, Executors.defaultThreadFactory());
        server = AsynchronousServerSocketChannel.open(group).bind(new InetSocketAddress("127.0.0.1", 9090));

        server.accept(null, new CompletionHandler<AsynchronousSocketChannel, Object>() {
            @Override
            public void completed(AsynchronousSocketChannel result, Object attachment) {
                if (server.isOpen()) {
                    server.accept(null, this);
                }
                worker = result;
                if ((worker != null) && (worker.isOpen())) {
                    ByteBuffer byteBuffer = ByteBuffer.allocate(100);
                    worker.read(byteBuffer);
                    System.out.println("received the client: "+new String(byteBuffer.array()));
                }
            }
            @Override
            public void failed(Throwable exc, Object attachment) {
                //TODO
            }
        });
//        if (!group.isShutdown()) {
//            System.out.println("Shutdown channel group");
//            //关闭分组
//            group.shutdown();
//        }
//        if (!group.isTerminated()) {
//            System.out.println("Terminate channel group");
//            //强制关闭，通道将被关闭，读取中止
//            group.shutdownNow();
//        }

    }
    public static void main(String[] args) throws InterruptedException, ExecutionException, IOException, TimeoutException {
        AsyncEchoServerWithCallBack server = new AsyncEchoServerWithCallBack();

    }
}
