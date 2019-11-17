package com.pjmike.concurrency.lock;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @description:
 * @author: pjmike
 * @create: 2019/04/12 21:25
 */
public class ReetrantLockQuestion {
    private static ReentrantLock lock = new ReentrantLock();
    public static void main(String[] args) {

    }

    private static void lock() {
        try {
            lock.lockInterruptibly();
        } catch (InterruptedException e) {
            //显示恢复中断标识
            Thread.currentThread().interrupt();
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}
