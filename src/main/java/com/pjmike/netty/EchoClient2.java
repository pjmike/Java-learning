package com.pjmike.netty;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.net.InetSocketAddress;
import java.util.concurrent.TimeUnit;

/**
 * @author pjmike
 * @create 2018-09-15 15:51
 */
public class EchoClient2 {
    private final String host;
    private final int port;
    private EventLoopGroup group = new NioEventLoopGroup();

    public EchoClient2(String host, int port) {
        this.host = host;
        this.port = port;
    }

    public void start(){
        System.out.println("开启连接Netty服务端");
        Bootstrap b = new Bootstrap();
        b.group(group)
                .channel(NioSocketChannel.class)
                .remoteAddress(new InetSocketAddress(host, port))
                .handler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel socketChannel) throws Exception {
                        socketChannel.pipeline().addLast(new EchoClientHandler());
                    }
                });
        doConnect(b);
    }

    public void doConnect(Bootstrap bootstrap) {
        ChannelFuture future = bootstrap.connect();
        future.addListener((ChannelFutureListener) future1 -> {
            if (future1.isSuccess()) {
                System.out.println("连接成功");
            } else {
                System.out.println("连接失败，断线重连");
                future1.channel().eventLoop().schedule(new Runnable() {
                    @Override
                    public void run() {
                        start();
                    }
                }, 20, TimeUnit.SECONDS);
            }
        });
    }

    public static void main(String[] args) {
        new EchoClient2("127.0.0.1", 8888).start();
    }
}
