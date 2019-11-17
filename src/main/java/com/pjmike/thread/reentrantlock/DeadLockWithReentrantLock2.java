package com.pjmike.thread.reentrantlock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author pjmike
 * @create 2018-11-22 17:26
 */
public class DeadLockWithReentrantLock2 implements Runnable{
    private boolean flag;
    //锁1
    private static ReentrantLock lock1 = new ReentrantLock();
    //锁2
    private static ReentrantLock lock2 = new ReentrantLock();

    public DeadLockWithReentrantLock2(boolean flag) {
        this.flag = flag;
    }

    @Override
    public void run() {
        try {
            if (flag) {
                //获取锁
                if (lock1.tryLock()) {
                    System.out.println("线程 ： " + Thread.currentThread().getName() + " get lock1");
                    TimeUnit.SECONDS.sleep(2);
                    System.out.println("线程 ： " + Thread.currentThread().getName() + " try to get lock2");
                    if (lock2.tryLock()) {
                        System.out.println("线程 ： " + Thread.currentThread().getName() + " already get lock2");
                    }
                }
            } else {
                if (lock2.tryLock()) {
                    System.out.println("线程 ： " + Thread.currentThread().getName() + " get lock2");
                    TimeUnit.SECONDS.sleep(2);
                    System.out.println("线程 ： " + Thread.currentThread().getName() + " try to get lock1");
                    if (lock1.tryLock()) {
                        System.out.println("线程 ： " + Thread.currentThread().getName() + " already get lock1");
                    }
                }
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
        Thread t1 = new Thread(new DeadLockWithReentrantLock2(true));
        t1.setName("A");
        Thread t2 = new Thread(new DeadLockWithReentrantLock2(false));
        t2.setName("B");
        t1.start();
        t2.start();
        TimeUnit.SECONDS.sleep(5);
    }
}
