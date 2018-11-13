package com.pjmike.netty.bytebuf;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
import io.netty.buffer.PooledByteBufAllocator;
import io.netty.buffer.Unpooled;

import java.nio.charset.Charset;
import java.util.Arrays;

/**
 * @author pjmike
 * @create 2018-09-16 12:21
 */
public class ByteBufTest {
    public static void main(String[] args) {
        ByteBufAllocator allocator = new PooledByteBufAllocator();
        ByteBuf buf1 = allocator.buffer();
        buf1.writeBytes("hello world".getBytes());
        System.out.println("原始ByteBuf: " + buf1.toString(Charset.forName("UTF-8")));
        byte b1 = buf1.readByte();
        byte b2 = buf1.readByte();
        System.out.println("读出的 bytes: " + Arrays.toString(new byte[]{b1, b2}));
        System.out.println("readerIndex 索引位置： "+buf1.readerIndex());
    }
}
