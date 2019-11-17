package com.pjmike.thread.reentrantlock;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @author pjmike
 * @create 2018-11-22 16:23
 */
public class ReenterLock implements Runnable{
    private ReentrantLock lock = new ReentrantLock();
    private int i = 0;
    @Override
    public void run() {
        for (int j = 0; j < 1000000 ; j++) {
            //获取锁
            lock.lock();
            try{
                i++;
            } finally {
                //释放锁
                lock.unlock();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ReenterLock reenterLock = new ReenterLock();
        Thread t1 = new Thread(reenterLock);
        Thread t2 = new Thread(reenterLock);
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println(reenterLock.i);
    }
}
