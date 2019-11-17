package com.pjmike.concurrency.barrier;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @description:
 * @author: pjmike
 * @create: 2019/04/12 21:47
 */
public class CountDownLatchQuestion {
    public static void main(String[] args) throws InterruptedException {
        //倒计时5
        CountDownLatch latch = new CountDownLatch(5);

        ExecutorService executorService = Executors.newFixedThreadPool(5);

        for (int i = 0; i <4 ; i++) {
            executorService.submit(() -> {
                action();
                latch.countDown();//-1
            });
        }
        //等待完成
        //当计数>0会被阻塞
        latch.await();
        System.out.println("Done");//会被阻塞
        //关闭线程池
        executorService.shutdown();
    }
    private static void action() {
        System.out.printf("线程[%s]正在执行 \n", Thread.currentThread().getName());
    }
}
