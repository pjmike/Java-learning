package com.pjmike.thread.reentrantlock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author pjmike
 * @create 2018-11-16 21:37
 */
public class ReentrantLockExample {
    int a = 0;
    ReentrantLock lock = new ReentrantLock();

    public void writer() {
        lock.lock();               //获取锁
        try {
            a++;
        } finally {
            lock.unlock();         //释放锁
        }
    }

    public void reader() {
        lock.lock();               //获取锁
        try{
            int i = a;
            System.out.println("i : " + a);
        } finally {
            lock.unlock();         //释放锁
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ReentrantLockExample reentrantLockExample = new ReentrantLockExample();
        for (int i = 0; i < 100; i++) {
            new Thread(() -> reentrantLockExample.writer());
        }
        TimeUnit.SECONDS.sleep(10);
        reentrantLockExample.reader();
    }
}
