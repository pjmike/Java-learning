package com.pjmike.thread;

/**
 * @author pjmike
 * @create 2018-11-19 16:15
 */
public class Thread2 implements Runnable{

    @Override
    public void run() {
        System.out.println("hello world");
    }

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread() {
            @Override
            public void run() {
                while (true) {
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        System.out.println("Interrupted When Sleep");
                        //中断异常会清楚中断标记，重新设置中断状态
                        Thread.currentThread().interrupt();
                    }
                    if (Thread.currentThread().isInterrupted()) {
                        System.out.println("Interrupted!");
                        break;
                    }
                }
            }
        };
        thread.start();
        Thread.sleep(2000);
        thread.interrupt();
    }
}
