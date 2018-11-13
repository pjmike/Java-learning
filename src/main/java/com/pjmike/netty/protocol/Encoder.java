package com.pjmike.netty.protocol;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author pjmike
 * @create 2018-09-30 15:58
 */
public class Encoder extends MessageToByteEncoder {
    private AtomicInteger integer = new AtomicInteger();
    private static final Serializer serializer = new JSONSerilaizer();
    private Class<?> genericClass;

    public Encoder(Class<?> genericClass) {
        this.genericClass = genericClass;
    }
    @Override
    protected void encode(ChannelHandlerContext ctx, Object msg, ByteBuf out) throws Exception {
        if (genericClass.isInstance(msg)) {
            byte[] data = serializer.serialize(msg);
            //设置魔数
            out.writeByte(Protocol.MAGIC_NUMBER);
            //设置序列化方式
            out.writeByte(serializer.getSerializerAlgorithm());
            //设置 Requset ID
            out.writeByte(integer.incrementAndGet());
            //设置数据长度
            out.writeInt(data.length);
            //设置数据
            out.writeBytes(data);
        }
    }
}
