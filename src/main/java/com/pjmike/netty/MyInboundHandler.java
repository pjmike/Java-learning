package com.pjmike.netty;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.EventLoop;
import io.netty.channel.SimpleChannelInboundHandler;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * @author pjmike
 * @create 2018-10-28 11:09
 */
public class MyInboundHandler extends SimpleChannelInboundHandler {
    private Client client;
    public MyInboundHandler(Client client) {
        this.client = client;
    }
    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        final EventLoop eventLoop = ctx.channel().eventLoop();
        eventLoop.schedule(() -> {
            client.createBootstrap(new Bootstrap(), eventLoop);
        }, 10L, TimeUnit.SECONDS);
        super.channelInactive(ctx);
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Object msg) throws Exception {

    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        ctx.writeAndFlush(Unpooled.copiedBuffer("hello world".getBytes()));
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        EventLoop eventLoop = ctx.channel().eventLoop();
        if (cause instanceof IOException) {
            eventLoop.schedule(() -> {
                client.createBootstrap(new Bootstrap(), eventLoop);
            }, 10L, TimeUnit.SECONDS);
        }
        ctx.channel().close();
    }
}
