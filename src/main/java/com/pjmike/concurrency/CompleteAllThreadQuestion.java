package com.pjmike.concurrency;

/**
 * @description: 主线程退出，所有线程退出
 * @author: pjmike
 * @create: 2019/04/07 22:25
 */
public class CompleteAllThreadQuestion {
    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(CompleteAllThreadQuestion::action, "t1");
        Thread t2 = new Thread(CompleteAllThreadQuestion::action, "t2");
        Thread t3 = new Thread(CompleteAllThreadQuestion::action, "t3");
        Thread mainThread = Thread.currentThread();
        t1.start();
        t2.start();
        t3.start();
        //获取main线程组
        ThreadGroup threadGroup = mainThread.getThreadGroup();

        //活跃的线程数
        int count = threadGroup.activeCount();
        Thread[] threads = new Thread[count];
        threadGroup.enumerate(threads, true);
        for (Thread thread : threads) {
            System.out.printf("当前活跃线程[%s]",thread.getName());
        }
    }
    private static void action() {
        System.out.printf("线程[%s]正在执行。。", Thread.currentThread().getName());
    }
}
