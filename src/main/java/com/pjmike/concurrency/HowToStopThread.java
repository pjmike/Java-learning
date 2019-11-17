package com.pjmike.concurrency;

/**
 * @description: 如何停止一个线程
 * @author: pjmike
 * @create: 2019/04/07 20:51
 */
public class HowToStopThread {
    public static void main(String[] args) throws InterruptedException {
        Action action = new Action();
        Thread thread = new Thread(action, "t1");
        thread.start();
        //改变action的状态
        action.setStopped(true);
        thread.join();

        Thread t2 = new Thread(() -> {
            if (!Thread.currentThread().isInterrupted()) {
                action();
            }
        }, "t2");
        t2.start();
        t2.isInterrupted();
        t2.join();
    }

    private static class Action implements Runnable {
        //线程安全问题,确保可见性，开关的方式
        private volatile boolean stopped = false;
        @Override
        public void run() {
            if (!stopped) {
                action();
            }
        }

        public void setStopped(boolean stopped) {
            this.stopped = stopped;
        }
    }

    private static void action() {
        System.out.printf("线程[%s]正在执行。。", Thread.currentThread().getName());
    }
}
