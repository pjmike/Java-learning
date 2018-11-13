package com.pjmike.nio;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @author pjmike
 * @create 2018-08-24 15:24
 */
public class ChannelTest {
    public static void main(String[] args) throws IOException {
        RandomAccessFile accessFile = new RandomAccessFile("C:\\Users\\Administrator\\Desktop\\nio-data.txt", "rw");
        //获取FileChannel
        FileChannel fileChannel = accessFile.getChannel();
        ByteBuffer byteBuffer = ByteBuffer.allocate(48);
        //从通道里读取数据到缓冲区
        int bytesRead = fileChannel.read(byteBuffer);
        while (bytesRead > 0) {
            System.out.println("Read: "+bytesRead);
            //反转
            byteBuffer.flip();
            //从缓冲区中读取数据
            while (byteBuffer.hasRemaining()) {
                System.out.print((char)byteBuffer.get());
            }
            byteBuffer.clear();
            bytesRead = fileChannel.read(byteBuffer);
        }
        accessFile.close();
    }
}
