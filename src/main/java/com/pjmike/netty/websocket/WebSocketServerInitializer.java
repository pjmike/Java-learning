package com.pjmike.netty.websocket;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;
import io.netty.handler.codec.http.websocketx.extensions.compression.WebSocketServerCompressionHandler;
import io.netty.handler.ssl.SslContext;
import io.netty.handler.stream.ChunkedWriteHandler;

/**
 * @author pjmike
 * @create 2018-09-26 20:35
 */
public class WebSocketServerInitializer extends ChannelInitializer<SocketChannel> {
    final SslContext sslContext;
    public WebSocketServerInitializer(SslContext sslContext) {
        this.sslContext = sslContext;
    }

    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();
        if (sslContext != null) {
            pipeline.addLast(sslContext.newHandler(ch.alloc()));
        }
        //HTTP编解码器
        pipeline.addLast(new HttpServerCodec());
        //HTTP聚合器
        pipeline.addLast(new HttpObjectAggregator(64 * 1024));
        //写入一个文件的内容
        pipeline.addLast(new ChunkedWriteHandler());
        //按照 WebSocket规范的要求，处理WebSocket升级握手，PingWebSocketFrame、PongWebSocketFrame和CloseWebSocketFrame
        pipeline.addLast(new WebSocketServerProtocolHandler("/ws",null,true));
        //处理FullHttpRequest
        pipeline.addLast(new WebSocketIndexPageHandler());
        //处理TextWebSocketFrame 和握手完成事件
        pipeline.addLast(new TextWebSocketFrameHandler());
    }
}
