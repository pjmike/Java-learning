package com.pjmike.java8.future;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CountDownLatch;

/**
 * @description:
 * @author: 13572
 * @create: 2019/09/21 16:55
 */
public class CompletableFutureDemo {
    public static void main(String[] args) throws InterruptedException {
        long l = System.currentTimeMillis();

        CompletableFuture<Integer> completableFuture = CompletableFuture.supplyAsync(() -> {
            System.out.println("在回调中执行耗时操作...");
            delay();
            return 100;
        });
        completableFuture = completableFuture.thenCompose(integer -> {
            return CompletableFuture.supplyAsync(() -> {
                System.out.println("在回调的回调中执行耗时操作...");
                delay();
                return integer + 100;
            });
        });//<1>

        completableFuture.whenComplete((result, throwable) -> System.out.println("计算结果: " + result));
        System.out.println("主线程运算耗时：" + (System.currentTimeMillis() - l)  + "ms");
        new CountDownLatch(1).await();
    }

    static void delay() {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
