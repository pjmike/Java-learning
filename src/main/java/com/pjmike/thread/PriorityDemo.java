package com.pjmike.thread;

/**
 * @author pjmike
 * @create 2018-11-20 15:59
 */
public class PriorityDemo {
    private static int count = 0;
    public static void main(String[] args) {
        Thread A = new Thread() {
            @Override
            public void run() {
                while (true) {
                    synchronized (PriorityDemo.class) {
                        count++;
                        if (count > 100000) {
                            System.out.println("HighPriority is complete");
                            break;
                        }
                    }
                }
            }
        };
        Thread B = new Thread() {
            @Override
            public void run() {
                while (true) {
                    synchronized (PriorityDemo.class) {
                        count++;
                        if (count > 100000) {
                            System.out.println("LowPriority is complete");
                            break;
                        }
                    }
                }
            }
        };
        //设置线程A为高优先级
        A.setPriority(Thread.MAX_PRIORITY);
        //设置线程B为低优先级
        B.setPriority(Thread.MIN_PRIORITY);
        //启动线程B
        B.start();
        //启动线程A
        A.start();
    }
}
