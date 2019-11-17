package com.pjmike.jvm.bytecode;

/**
 * @description:
 * @author: 13572
 * @create: 2019/04/27 11:59
 */
public class HelloTest {
    public static void main(String[] args) {
        System.out.println("hello world");
    }

    private Object foo(int i, double j, Thread thread) {
        System.out.println("thread:"+thread.getName()+"");
        return new Object();
    }
}
