package com.pjmike.thread;

/**
 * @author pjmike
 * @create 2018-11-20 15:52
 */
public class DaemonDemo {
    public static void main(String[] args) throws InterruptedException {
        Thread t = new Thread() {
            @Override
            public void run() {
                while (true) {
                    System.out.println("I am alive");
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };
        //将线程t设置为守护线程
        t.setDaemon(true);
        t.start();

        Thread.sleep(10000);
    }
}
