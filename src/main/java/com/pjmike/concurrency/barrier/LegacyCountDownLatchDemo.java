package com.pjmike.concurrency.barrier;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @description:
 * @author: pjmike
 * @create: 2019/04/12 21:47
 */
public class LegacyCountDownLatchDemo {
    /**
     * java 1.4 synchronized实现
     */
    private static class LegacyCountDownLatch {
        private int counter;

        public LegacyCountDownLatch(int counter) {
            this.counter = counter;
        }

        public void await() throws InterruptedException {
            // 当count > 0需等待
            if (Thread.interrupted()) {
                throw new InterruptedException();
            }
            synchronized (this) {
                while (counter > 0) {
                    wait(); //阻塞当前线程
                }
            }
        }
        public void countDown() {
            synchronized (this) {
                if (counter < 1) {
                    return;
                }
                counter--;
                if (counter == 0) { //当数量减少至0时，唤起被阻塞线程
                    notifyAll();
                }
            }
        }

    }

    /**
     * java 1.5 + Lock实现
     */
    private static class LegacyCountDownLatchWithLock {
        private int counter;
        private final Lock lock = new ReentrantLock();
        private final Condition condition = lock.newCondition();
        public LegacyCountDownLatchWithLock(int counter) {
            this.counter = counter;
        }

        public void await() throws InterruptedException {
            // 当count > 0需等待
            if (Thread.interrupted()) {
                throw new InterruptedException();
            }
            lock.lock();
            try {
                while (counter > 0) {
                    condition.await();
                }
            } finally {
                lock.unlock();
            }
        }
        public void countDown() {
            lock.lock();
            try {
                if (counter < 1) {
                    return;
                }
                counter--;
                if (counter == 0) { //当数量减少至0时，唤起被阻塞线程
                    condition.signalAll();
                }
            } finally {
                lock.unlock();
            }
        }

    }
    private static void action() {
        System.out.printf("线程[%s]正在执行 \n", Thread.currentThread().getName());
    }
}
