package com.pjmike.thread.reentrantlock;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @author pjmike
 * @create 2018-11-22 16:23
 */
public class ReenterLock_2 implements Runnable {
    private ReentrantLock lock = new ReentrantLock();
    private int i = 0;

    @Override
    public void run() {
        //获取锁
        lock.lock();
        try {
            Thread.sleep(10000);
            i++;
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            //释放锁
            lock.unlock();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ReenterLock_2 reenterLock = new ReenterLock_2();
        Thread t1 = new Thread(reenterLock,"Thread-A");
        Thread t2 = new Thread(reenterLock,"Thread-B");
        t1.start();
        Thread.sleep(3000);
        t2.start();
        t1.join();
        t2.join();
        System.out.println(reenterLock.i);
    }

}
