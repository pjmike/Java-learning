package com.pjmike.thread;

/**
 * @author pjmike
 * @create 2018-09-16 16:33
 */
public class MyThread1 extends Thread{
    public MyThread1(){

        System.out.println("------" + "构造函数开始" + "------");
        System.out.println("Thread.currentThread().getName() = " + Thread.currentThread().getName());
        System.out.println("Thread.currentThread().isAlive() = " + Thread.currentThread().isAlive());
        System.out.println("this.getName() = " + this.getName());
        System.out.println("this.isAlive() = " + this.isAlive());
        System.out.println("------" + "构造函数结束" + "------");

    }

    @Override
    public void run(){

        Thread testThread = Thread.currentThread();

        System.out.println("------" + "run()开始" + "------");
        System.out.println("Thread.currentThread().getName() = " + Thread.currentThread().getName());
        System.out.println("Thread.currentThread().isAlive() = " + Thread.currentThread().isAlive());
        System.out.println("this.getName() = " + this.getName());
        System.out.println("this.isAlive() = " + this.isAlive());
        System.out.println("Thread.currentThread() == this : " + (Thread.currentThread() == this));
        System.out.println("------" + "run()结束" + "------");

    }

    public static void main(String[] args) {
        MyThread1 myThread1 = new MyThread1();
//        myThread1.setName("A");
//        myThread1.start();
        Thread thread = new Thread(myThread1);
        thread.start();
    }

}
