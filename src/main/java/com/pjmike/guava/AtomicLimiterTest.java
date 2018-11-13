package com.pjmike.guava;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author pjmike
 * @create 2018-09-13 23:40
 */
public class AtomicLimiterTest implements Runnable{
    private static final AtomicInteger atomic = new AtomicInteger(0);


    public static void main(String[] args) {
        for (int i = 0; i < 5; i++) {
            AtomicLimiterTest atomicLimiterTest = new AtomicLimiterTest();
            new Thread(atomicLimiterTest).start();
        }
    }

    @Override
    public void run() {
        if (atomic.get() >= 3) {
            System.out.println("超出限流数，拒绝请求");
        } else {
            atomic.incrementAndGet();
            System.out.println("当前计数为: "+atomic.get());
            //处理业务逻辑
        }
    }
}
