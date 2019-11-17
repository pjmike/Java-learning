package com.pjmike.thread;

import java.util.concurrent.CountDownLatch;

/**
 * @description:
 * @author: pjmike
 * @create: 2019/01/22 15:50
 */
public class CountDownLatchTest implements Runnable{
    final static CountDownLatch latch = new CountDownLatch(2);
    @Override
    public void run() {
        try {
            System.out.println("线程："+Thread.currentThread().getName()+" 正在执行");
            Thread.sleep(3000);
            System.out.println("线程："+Thread.currentThread().getName()+" 执行完毕");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            latch.countDown();
        }
    }

    public static void main(String[] args) {
        Thread A = new Thread(new CountDownLatchTest(), "A");
        Thread B = new Thread(new CountDownLatchTest(), "B");
        A.start();
        B.start();
        try {
            System.out.println("等待线程A和线程B执行完成");
            latch.await();
            System.out.println("线程A和线程B执行完毕");
            System.out.println("主线程继续执行");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
