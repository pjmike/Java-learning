package com.pjmike.concurrency;

/**
 * @description:
 * @author: pjmike
 * @create: 2019/04/07 20:30
 */
public class ThreadStateQuestion {
    public static void main(String[] args) {
        Thread thread = new Thread(() -> {
            System.out.printf("线程[%s]正在执行。。", Thread.currentThread().getName());
        }, "子线程-1");
        thread.start();

        System.out.printf("线程[%s]还活着,", thread.isAlive());
        //在Java中，执行线程Java是没有办法销毁的

    }
}
