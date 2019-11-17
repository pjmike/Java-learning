package com.pjmike.thread;

/**
 * @author pjmike
 * @create 2018-11-19 18:22
 */
public class Thread4 {
    public volatile static int i = 0;
    public static class Thread_ extends Thread{
        @Override
        public void run() {
            for (i = 0; i < 1000000; i++) {

            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread_ thread_ = new Thread_();
        thread_.start();
        //让当前的主线程等待thread_线程执行完毕后才往下执行
        thread_.join();
        Thread.yield();
        System.out.println(i);
    }
}
