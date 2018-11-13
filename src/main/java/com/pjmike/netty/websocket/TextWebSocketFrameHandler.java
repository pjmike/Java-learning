package com.pjmike.netty.websocket;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;

import java.util.Locale;
import java.util.logging.Logger;

/**
 * @author pjmike
 * @create  2018-09-26 21:41
 */
public class TextWebSocketFrameHandler extends SimpleChannelInboundHandler<Object> {
    private static final Logger logger = Logger.getLogger(TextWebSocketFrame.class.getName());
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Object msg) throws Exception {
        if (msg instanceof TextWebSocketFrame) {
            //接入 WebSocket连接
            handleTextWebSocketFrame(ctx, (TextWebSocketFrame)msg);
        }
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        ctx.flush();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }

    private void handleTextWebSocketFrame(ChannelHandlerContext ctx, TextWebSocketFrame msg) {
        String request = msg.text();
        logger.info(String.format("%s 接收到的消息 %s", ctx.channel(), request));
        ctx.channel().writeAndFlush(new TextWebSocketFrame(request.toUpperCase(Locale.US)));
    }
}
