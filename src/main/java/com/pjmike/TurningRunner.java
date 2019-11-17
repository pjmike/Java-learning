package com.pjmike;

/**
 * @description: 多线程交替打印100以内的奇数和偶数
 * @author: pjmike
 * @create: 2019/03/12 11:56
 *
 */
public class TurningRunner {
    private int count = 0;
    private final Object lock = new Object();
    public void turning() throws InterruptedException {
        Thread even = new Thread(() -> {
            while (count <= 100) {
                synchronized (lock) {
                    System.out.println("偶数： " + count++);
                    lock.notify();
                    if (count <= 100) {
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        });

        Thread odd = new Thread(() -> {
            while (count <= 100) {
                synchronized (lock) {
                    System.out.println("奇数： " + count++);
                    lock.notify();
                    if (count <= 100) {
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        });
        even.start();
        Thread.sleep(1);
        odd.start();
    }

    public static void main(String[] args) throws InterruptedException {
        new TurningRunner().turning();
    }
}
