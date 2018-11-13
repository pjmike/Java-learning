package com.pjmike.thread;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * @author pjmike
 * @create 2018-09-16 17:52
 */
public class ExecutorTest {
    private static Executor executor = Executors.newFixedThreadPool(10);

    public static void main(String[] args) {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                System.out.println("Thread name: " + Thread.currentThread().getName());
            }
        });
    }
}
