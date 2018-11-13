package com.pjmike.netty;

import io.netty.buffer.ByteBuf;
import io.netty.channel.*;
import io.netty.util.internal.logging.Slf4JLoggerFactory;

import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

/**
 * @author pjmike
 * @create 2018-09-15 15:45
 */
@ChannelHandler.Sharable
public class EchoClientHandler extends ChannelInboundHandlerAdapter {
    private EchoClient client = new EchoClient("127.0.0.1",8888);

//    @Override
//    protected void channelRead0(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf) throws Exception {
//        System.out.println("Client received: " + byteBuf.toString());
//    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
//        for (int i = 0; i < 10000; i++) {
        ByteBuf buf = ctx.alloc().buffer();
        buf.writeBytes("keep hungry,keep study".getBytes());
        ctx.channel().writeAndFlush(buf);
//        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        System.out.println("捕获的异常消息: "+cause.getMessage());
//        cause.printStackTrace();
//        ctx.close();
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("服务端中途挂了，断线重连");
        final EventLoop eventLoop = ctx.channel().eventLoop();
        eventLoop.schedule(() -> client.start(), 10, TimeUnit.SECONDS);
        super.channelInactive(ctx);
    }
}
