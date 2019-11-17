package com.pjmike.concurrency.barrier;

import java.util.concurrent.*;

/**
 * @description:
 * @author: pjmike
 * @create: 2019/04/12 21:53
 */
public class CyclicBarrierQuestion {
    public static void main(String[] args) throws InterruptedException {
        CyclicBarrier cyclicBarrier = new CyclicBarrier(5);
        ExecutorService executorService = Executors.newFixedThreadPool(5);

        for (int i = 0; i <20 ; i++) {
            executorService.submit(() -> {
                action();
                try {
                    //当计数>0时候阻阻塞
                    //CyclicBarrier await()方法等于 CountDownLatch.countDown() + await();
                    //先计数-1,再判断计数>0，才阻塞
                    cyclicBarrier.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            });
        }
        //尽可能不要执行完成再reset
        executorService.awaitTermination(3, TimeUnit.SECONDS);
        //这里是一个废操作
        cyclicBarrier.reset();
        System.out.println("done");
        executorService.shutdown();
    }
    private static void action() {
        System.out.printf("线程[%s]正在执行 \n", Thread.currentThread().getName());
    }
}
