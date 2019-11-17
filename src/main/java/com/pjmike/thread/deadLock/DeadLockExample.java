package com.pjmike.thread.deadLock;

import java.util.concurrent.TimeUnit;

/**
 * @author pjmike
 * @create 2018-11-22 17:01
 */
public class DeadLockExample implements Runnable{
    private boolean flag;
    //锁1
    private static Object lock1 = new Object();
    //锁2
    private static Object lock2 = new Object();

    public DeadLockExample(boolean flag) {
        this.flag = flag;
    }

    @Override
    public void run() {
        if (flag) {
            synchronized (lock1) {
                System.out.println("线程 ： " + Thread.currentThread().getName() + " get lock1");
                try {
                    TimeUnit.SECONDS.sleep(2);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                //尝试获取lock2
                System.out.println("线程 :  "+ Thread.currentThread().getName()+" waiting get lock2");
                synchronized (lock2) {
                    System.out.println("线程 ： " + Thread.currentThread().getName() + " get lock1");
                }
            }
        } else {
            synchronized (lock2) {
                System.out.println("线程 ： " + Thread.currentThread().getName() + " get lock2");
                try {
                    TimeUnit.SECONDS.sleep(2);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                //尝试获取锁1
                System.out.println("线程 :  "+ Thread.currentThread().getName()+" waiting get lock1");
                synchronized (lock1) {
                    System.out.println("线程 ： " + Thread.currentThread().getName() + " get lock1");
                }
            }
        }
    }

    public static void main(String[] args) {
        Thread t1 = new Thread(new DeadLockExample(true));
        t1.setName("A");
        Thread t2 = new Thread(new DeadLockExample(false));
        t2.setName("B");
        t1.start();
        t2.start();
    }
}
