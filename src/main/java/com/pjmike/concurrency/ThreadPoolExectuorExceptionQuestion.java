package com.pjmike.concurrency;

import java.util.concurrent.*;

/**
 * @description:
 * @author: pjmike
 * @create: 2019/04/07 21:25
 */
public class ThreadPoolExectuorExceptionQuestion {
    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        ThreadPoolExecutor executor = new ThreadPoolExecutor(
                1,
                1,
                0,
                TimeUnit.MILLISECONDS,
                new LinkedBlockingDeque<>()
        ) {
            @Override
            protected void afterExecute(Runnable r, Throwable t) {
//                System.out.printf(”"线程[%s]", Thread.currentThread().getName());
            }
        };

        executorService.execute(()-> {
            throw new RuntimeException("数组达到阈值");
        });
        //等待一秒钟，确保提交的任务完成
        executorService.awaitTermination(1, TimeUnit.SECONDS);
        //关闭线程池
        executorService.shutdown();
    }
}
