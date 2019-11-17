package com.pjmike.thread.condition;


import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @description:
 * @author: pjmike
 * @create: 2019/01/18 19:49
 */
public class ReentLockCondition implements Runnable{
    public static ReentrantLock lock = new ReentrantLock();
    public static Condition condition = lock.newCondition();
    @Override
    public void run() {
        try {
            lock.lock();
            condition.await();
            System.out.println("The Thread is going on");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ReentLockCondition t = new ReentLockCondition();
        Thread thread = new Thread(t);
        thread.start();
        Thread.sleep(2000);
//        lock.lock();
//        condition.signal();
//        lock.unlock();
        thread.interrupt();
    }
}
