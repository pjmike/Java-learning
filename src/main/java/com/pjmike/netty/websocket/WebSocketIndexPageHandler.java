package com.pjmike.netty.websocket;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.handler.codec.http.*;
import io.netty.handler.ssl.SslHandler;
import io.netty.util.CharsetUtil;

/**
 * @author pjmike
 * @create 2018-09-27 16:31
 */
public class WebSocketIndexPageHandler extends SimpleChannelInboundHandler<FullHttpRequest> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, FullHttpRequest req) throws Exception {
        //处理一个错误请求
        if (!req.decoderResult().isSuccess()) {
            sendHttpResponse(ctx, req, new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.BAD_REQUEST));
            return;
        }
        //只允许 GET 请求
        if (req.method() != HttpMethod.GET) {
            sendHttpResponse(ctx, req, new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.FORBIDDEN));
            return;
        }
    }
    private void sendHttpResponse(ChannelHandlerContext ctx, FullHttpRequest req, FullHttpResponse fullHttpResponse) {
        //如果响应状态码不是 200,生成一个错误页面
        if (fullHttpResponse.status().code() != 200) {
            ByteBuf buf = Unpooled.copiedBuffer(fullHttpResponse.status().toString(), CharsetUtil.UTF_8);
            fullHttpResponse.content().writeBytes(buf);
            buf.release();
            HttpUtil.setContentLength(fullHttpResponse, fullHttpResponse.content().readableBytes());
        }
        //向客户端发送响应
        ChannelFuture future = ctx.channel().writeAndFlush(fullHttpResponse);
        if (!HttpUtil.isKeepAlive(req) || fullHttpResponse.status().code() != 200) {
            future.addListener(ChannelFutureListener.CLOSE);
        }
    }
}
