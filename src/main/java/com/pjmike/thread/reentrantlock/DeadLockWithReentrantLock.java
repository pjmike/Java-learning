package com.pjmike.thread.reentrantlock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author pjmike
 * @create 2018-11-22 17:26
 */
public class DeadLockWithReentrantLock implements Runnable{
    private boolean flag;
    //锁1
    private static ReentrantLock lock1 = new ReentrantLock();
    //锁2
    private static ReentrantLock lock2 = new ReentrantLock();

    public DeadLockWithReentrantLock(boolean flag) {
        this.flag = flag;
    }

    @Override
    public void run() {
        try {
            if (flag) {
                //获取锁
                lock1.lockInterruptibly();
                System.out.println("线程 ： " + Thread.currentThread().getName() + " get lock1");
                TimeUnit.SECONDS.sleep(2);
                System.out.println("线程 ： " + Thread.currentThread().getName() + " try to get lock2");
                lock2.lockInterruptibly();
            } else {
                lock2.lockInterruptibly();
                System.out.println("线程 ： " + Thread.currentThread().getName() + " get lock2");
                TimeUnit.SECONDS.sleep(2);
                System.out.println("线程 ： " + Thread.currentThread().getName() + " try to get lock1");
                lock1.lockInterruptibly();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            //如果当前线程持有锁1,释放锁1
            if (lock1.isHeldByCurrentThread()) {
                lock1.unlock();
            }
            //如果当前线程持有锁2,释放锁2
            if (lock2.isHeldByCurrentThread()) {
                lock2.unlock();
            }
            System.out.println("线程 ： " + Thread.currentThread().getName() + " 退出");
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(new DeadLockWithReentrantLock(true));
        t1.setName("A");
        Thread t2 = new Thread(new DeadLockWithReentrantLock(false));
        t2.setName("B");
        t1.start();
        t2.start();
        TimeUnit.SECONDS.sleep(5);
        System.out.println("线程B设置中断标记，线程B将退出死锁状态");
        t2.interrupt();

    }
}
