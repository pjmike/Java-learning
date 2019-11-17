package com.pjmike.java8.future;

import org.apache.tools.ant.util.DateUtils;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CountDownLatch;
import java.util.function.BiConsumer;

/**
 * @description:
 * @author: 13572
 * @create: 2019/09/21 16:04
 */
public class CompletableFutureTest {
    public static void main(String[] args) throws InterruptedException {
        long start = System.currentTimeMillis();
        CountDownLatch downLatch = new CountDownLatch(1);
        CompletableFuture<Integer> future = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            downLatch.countDown();
            return 100;
        });
        future.whenComplete((integer, throwable) -> System.out.println("回调结果: " + integer));
        long end = System.currentTimeMillis();
        downLatch.await();
        System.out.println("耗时：" + (end - start) / 1000);
    }
}
