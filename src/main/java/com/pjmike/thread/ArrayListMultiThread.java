package com.pjmike.thread;

import java.util.ArrayList;

/**
 * @author pjmike
 * @create 2018-11-20 16:39
 */
public class ArrayListMultiThread {
    static ArrayList<Integer> al = new ArrayList<>(10);

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread() {
            @Override
            public void run() {
                for (int i = 0; i < 100000; i++) {
                    al.add(i);
                }
            }
        };
        Thread t2 = new Thread() {
            @Override
            public void run() {
                for (int i = 0; i < 100000; i++) {
                    al.add(i);
                }
            }
        };
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println(al.size());
    }
}
