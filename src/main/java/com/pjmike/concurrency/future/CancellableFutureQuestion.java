package com.pjmike.concurrency.future;

import java.util.concurrent.*;

/**
 * @description:
 * @author: pjmike
 * @create: 2019/04/12 23:12
 */
public class CancellableFutureQuestion {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newSingleThreadExecutor();

        Future future = executorService.submit(() -> {
            //3s内执行正常
            action(3);
        });
        try {
            future.get(3, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            //Thread 恢复中断状态
//            Thread.currentThread().interrupt();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            //执行超时，适当关闭
            future.cancel(true);
        }
        executorService.shutdown();
    }

    private static void action(int seconds) {
        try {
            Thread.sleep(TimeUnit.SECONDS.toMillis(seconds));
            System.out.printf("线程[%s]正在执行。。", Thread.currentThread().getName());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
