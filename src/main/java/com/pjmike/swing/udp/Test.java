package com.pjmike.swing.udp;

/**
 * @description:
 * @author: pjmike
 * @create: 2019/10/18
 */
public class Test {
    public static void main(String[] args) {
        ChatServer r = new ChatServer();
        r.start();
        ChatClient c1 = new ChatClient("QQ", "张三");
        ChatClient c2 = new ChatClient("QQ", "李四");
        ChatClient c3 = new ChatClient("QQ", "王五");
    }
}
