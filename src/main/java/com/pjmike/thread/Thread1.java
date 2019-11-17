package com.pjmike.thread;

/**
 * @author pjmike
 * @create 2018-11-19 16:12
 */
public class Thread1 extends Thread{
    @Override
    public void run() {
        System.out.println("hello world");
    }

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(() -> {
            while (true) {
                //通过中断标志位判断是否被中断
                if (Thread.currentThread().isInterrupted()) {
                    //中断逻辑处理
                    System.out.println("Interrupted!");
                    // break退出循环
                    break;
                }
                //使当前线程从运行态变为就绪态
//                Thread.yield();
            }
        });
        //开启线程
        thread.start();
        Thread.sleep(2000);
        //设置中断标志位
        thread.interrupt();
    }
}
