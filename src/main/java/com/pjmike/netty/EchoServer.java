package com.pjmike.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.timeout.IdleStateHandler;

import java.net.InetSocketAddress;

/**
 * Echo服务端代码
 *
 * @author pjmike
 * @create 2018-09-15 11:57
 */
public class EchoServer {
    private final int port;

    public EchoServer(int port) {
        this.port = port;
    }

    public static void main(String[] args) throws InterruptedException {
        new EchoServer(8888).start();
    }

    public void start() throws InterruptedException {
        final EchoServerHandler serverHandler = new EchoServerHandler();
        //创建EventLoopGroup，处理事件
        EventLoopGroup group = new NioEventLoopGroup();
        EventLoopGroup worker = new NioEventLoopGroup();

        ServerBootstrap b = new ServerBootstrap();
        b.group(group, worker)
                //指定所使用的NIO传输 Channel
                .channel(NioServerSocketChannel.class)
                //使用指定的端口设置套接字地址
                .localAddress(new InetSocketAddress(port))
                //添加一个EchoServerHandler到子Channel的ChannelPipeline
                .childHandler(new ChannelInitializer<SocketChannel>() {

                    @Override
                    protected void initChannel(SocketChannel socketChannel) throws Exception {
                        //EchoServerHandler别标志为@Shareable,所以我们可以总是使用同样的实例
                        socketChannel.pipeline().addLast(new ServerIdleStateHandler());
                        socketChannel.pipeline().addLast(serverHandler);

                    }
                });
        //异步的绑定服务器，调用sync()方法阻塞等待直到绑定完成
        ChannelFuture future = b.bind().sync();
        future.channel().closeFuture().sync();
    }
}
