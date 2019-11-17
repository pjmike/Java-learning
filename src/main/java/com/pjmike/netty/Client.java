package com.pjmike.netty;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.nio.channels.SocketChannel;


/**
 * @author pjmike
 * @create 2018-10-28 11:08
 */
public class Client
{
    private EventLoopGroup loop = new NioEventLoopGroup();
    public static void main( String[] args )
    {

        new Client().run();
    }
    public Bootstrap createBootstrap(Bootstrap bootstrap, EventLoopGroup eventLoop) {
        if (bootstrap != null) {
            final MyInboundHandler handler = new MyInboundHandler(this);
            bootstrap.group(eventLoop);
            bootstrap.channel(NioSocketChannel.class);
            bootstrap.option(ChannelOption.SO_KEEPALIVE, true);
            bootstrap.handler(new ChannelInitializer<io.netty.channel.socket.SocketChannel>() {
                @Override
                protected void initChannel(io.netty.channel.socket.SocketChannel ch) throws Exception {
                    ch.pipeline().addLast(handler);
                }
            });
            bootstrap.remoteAddress("localhost", 8888);
            bootstrap.connect().addListener(new ConnectionListener(this));
        }
        return bootstrap;
    }
    public void run() {
        createBootstrap(new Bootstrap(), loop);
    }
}