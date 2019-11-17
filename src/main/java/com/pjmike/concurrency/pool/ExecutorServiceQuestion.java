package com.pjmike.concurrency.pool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @description:
 * @author: pjmike
 * @create: 2019/04/12 22:26
 */
public class ExecutorServiceQuestion {
    public static void main(String[] args) {
        /**
         * 1.5 ThreadPoolExecutor
         * scheduledThreadPoolExecutor -> ThreadPoolExecutor
         *
         * 1.7 ForkJoinPool
         */
        ExecutorService executorService = Executors.newFixedThreadPool(2);

        //当executorService 不再被使用，它会被GC -> finalize() -> shutdown()
        ExecutorService executorService2 = Executors.newSingleThreadExecutor();
    }
}
