package com.pjmike.thread;

/**
 * @description:
 * @author: 13572
 * @create: 2019/08/28 10:25
 */
public class ThreadStateTest {
    public static void main(String[] args) {
        System.out.println("Concurrent Thread State is: "+Thread.currentThread().getState().toString());

        try {
            Thread.sleep(3000);
            System.out.println("Concurrent Thread State is: " + Thread.currentThread().getState().toString());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Concurrent Thread State is: " + Thread.currentThread().getState().toString());
    }
}
