package com.pjmike.concurrency;

/**
 * @description:
 * @author: pjmike
 * @create: 2019/04/07 22:07
 */
public class DaemonThreadQuestion {
    public static void main(String[] args) {
        Thread t1 = new Thread(() -> {
            System.out.println("hello world");
        });
        //执行看时机的，主线程退出，守护线程
        t1.setDaemon(true);
        t1.start();
        //守护线程的执行是依赖于执行时间（非唯一评判）

    }
}
