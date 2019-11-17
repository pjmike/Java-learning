package com.pjmike.thread;

import java.util.concurrent.TimeUnit;

/**
 * @author pjmike
 * @create 2018-11-19 18:00
 */
public class Thread3 {
    public static void main(String[] args) {
        final Object object = new Object();
        new Thread(() -> {
            System.out.println("thread A is waiting to get lock");
            synchronized (object) {
                System.out.println("thread A already get lock");
                try {
                    TimeUnit.SECONDS.sleep(1);
                    System.out.println("thread A do wait method");
                    object.wait();
                    System.out.println("thread A wait end");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
        new Thread(() -> {
            System.out.println("thread B is waiting to get lock");
            synchronized (object) {
                System.out.println("thread B already get lock");
                try {
                    TimeUnit.SECONDS.sleep(5);
                    System.out.println("thread B do wait method");
                    object.notify();
                    System.out.println("thread B do notify method");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
