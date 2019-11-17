package com.pjmike.java8.future;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * @description:
 * @author: 13572
 * @create: 2019/09/21 15:47
 */

public class FutureTest {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        long start = System.currentTimeMillis();
        System.out.println("start: " + start);
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        Future<Integer> future = executorService.submit(() -> {
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return 100;
        });

        //do something
        Integer result = future.get();
        long end = System.currentTimeMillis();
        System.out.println(result);
        System.out.println("耗时：" + (end - start)/1000);
    }
}
