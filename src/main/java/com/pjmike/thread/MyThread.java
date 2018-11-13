package com.pjmike.thread;

/**
 * @author pjmike
 * @create 2018-09-16 16:15
 */
public class MyThread extends Thread{
    private int count = 5;
    @Override
    public void run() {
        count--;
        System.out.println("由："+currentThread().getName()+" 计算，count = "+count);
    }

    public static void main(String[] args) {
        MyThread myThread = new MyThread();
        myThread.start();
        System.out.println(myThread.isAlive());
        Thread a = new Thread(myThread, "a");
        Thread b = new Thread(myThread, "b");
        Thread c = new Thread(myThread, "c");
        a.start();
        System.out.println(a.isAlive());
        b.start();
        c.start();

    }
}
