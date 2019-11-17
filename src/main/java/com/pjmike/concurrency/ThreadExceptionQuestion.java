package com.pjmike.concurrency;

/**
 * @description:
 * @author: pjmike
 * @create: 2019/04/07 21:06
 */
public class ThreadExceptionQuestion {
    public static void main(String[] args) throws InterruptedException {
//        优雅处理
        Thread t1 = new Thread(() -> {
            throw new RuntimeException("数据达到阈值");
        }, "t1");
        Thread.setDefaultUncaughtExceptionHandler(new ExceptionHandler());
        t1.start();
        t1.join();
        //Java Thread是一个包装，它由GC做垃圾回收
        //JVM Thread  可能是一个OS Thread，JVM管理
        System.out.println(t1.isAlive());
    }

    static class ExceptionHandler implements Thread.UncaughtExceptionHandler {

        @Override
        public void uncaughtException(Thread t, Throwable e) {
            System.out.println("Exception:" + e.getMessage());
        }
    }
}
