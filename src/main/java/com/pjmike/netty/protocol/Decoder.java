package com.pjmike.netty.protocol;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;

/**
 * @author pjmike
 * @create 2018-09-30 15:38
 */
public class Decoder extends ByteToMessageDecoder {
    private static final Serializer serializer = new JSONSerilaizer();
    private Class<?> genericClass;

    public Decoder(Class<?> genericClass) {
        this.genericClass = genericClass;
    }

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        if (in.readableBytes() < 4) {
            return;
        }
        in.skipBytes(4);
        //序列化方式
        byte serializerType = in.readByte();
        //数据报长度
        int dataLength = in.readInt();
        byte[] data = new byte[dataLength];
        in.readBytes(data);
        Object obj = serializer.deserialize(genericClass, data);
        out.add(obj);
    }
}
