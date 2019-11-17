package com.pjmike.thread.reentrantlock;

import java.util.concurrent.locks.ReentrantLock;

/**
 * 公平锁
 *
 * @author pjmike
 * @create 2018-11-23 16:41
 */
public class FairLock implements Runnable{
    private static ReentrantLock lock = new ReentrantLock(true);
    @Override
    public void run() {
        while (true) {
            try {
                lock.lock();
                System.out.println(Thread.currentThread().getName() + " 获得锁 ");
            } finally {
                lock.unlock();
            }
        }
    }

    public static void main(String[] args) {
        FairLock fairLock = new FairLock();
        Thread A = new Thread(fairLock, "Thread-A");
        Thread B = new Thread(fairLock, "Thread-B");
        A.start();
        B.start();
    }
}
