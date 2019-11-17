package com.pjmike.jvm.bytecode;

/**
 * @description:
 * @author: 13572
 * @create: 2019/05/01 15:15
 */
public class Test {
    public static void main(String[] args) {
        ((Runnable) () -> System.out.println("hello world")).run();
    }
}
