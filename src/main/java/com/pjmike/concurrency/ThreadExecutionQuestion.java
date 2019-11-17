package com.pjmike.concurrency;

/**
 * @description:
 * @author: pjmike
 * @create: 2019/04/07 20:36
 */
public class ThreadExecutionQuestion {
    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(ThreadExecutionQuestion::action,"t1");
        Thread t2 = new Thread(ThreadExecutionQuestion::action,"t2");
        Thread t3 = new Thread(ThreadExecutionQuestion::action,"t3");
        t1.start();
    }
    //java thread 对象和实际JVM执行的OS thread不是相同对象
    //JVM thread回调 JAva Thread.run方法
    //当JVM thread执行后，自动就notify()
    private static void threadStartAndWait(Thread thread) throws InterruptedException {
        //当Thread isAlive() == false，thread.wait()操作会被自动释放
        if (thread.getState().equals(Thread.State.NEW)) {
            while (thread.isAlive()) {
                thread.wait();
            }
        }
    }
    private static void threadSleep() throws InterruptedException {
        Thread t1 = new Thread(ThreadExecutionQuestion::action,"t1");
        Thread t2 = new Thread(ThreadExecutionQuestion::action,"t2");
        Thread t3 = new Thread(ThreadExecutionQuestion::action,"t3");
        t1.start();
        while (t1.isAlive()) {
            Thread.sleep(0);
        }
        t2.start();
        while (t2.isAlive()) {
            Thread.sleep(0);
        }
        t3.start();
        while (t3.isAlive()) {
            Thread.sleep(0);
        }
    }

    private static void threadLoop() {
        Thread t1 = new Thread(ThreadExecutionQuestion::action,"t1");
        Thread t2 = new Thread(ThreadExecutionQuestion::action,"t2");
        Thread t3 = new Thread(ThreadExecutionQuestion::action,"t3");
        t1.start();
        while (t1.isAlive()) {
            //自旋
        }
        t2.start();
        while (t2.isAlive()) {
            //自旋
        }
        t3.start();
        while (t3.isAlive()) {
            //自旋
        }
    }

    private static void threadJoinOnnByOne() throws InterruptedException {
        Thread t1 = new Thread(ThreadExecutionQuestion::action,"t1");
        Thread t2 = new Thread(ThreadExecutionQuestion::action,"t2");
        Thread t3 = new Thread(ThreadExecutionQuestion::action,"t3");
        //start()仅是通知线程启动
        t1.start();
        t1.join();
        t2.start();
        t2.join();
        t3.start();
        //join() 控制线程必须执行完成
        t3.join();
    }
    private static void action() {
        System.out.printf("线程[%s]正在执行。。", Thread.currentThread().getName());
    }
}
