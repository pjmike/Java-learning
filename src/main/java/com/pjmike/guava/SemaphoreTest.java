package com.pjmike.guava;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * 信号量测试类
 *
 * @author pjmike
 * @create 2018-09-14 11:03
 */
public class SemaphoreTest {
    private static final int THRED_COUNT = 20;
    /**
     * 设置线程池大小为20
     */
    private static ExecutorService service = Executors.newFixedThreadPool(THRED_COUNT);
    /**
     * 设置信号量，表示只允许10个并发的执行
     */
    private static Semaphore semaphore = new Semaphore(10);
    public static void main(String[] args) {
        //30个线程在运行
        for (int i = 0; i < THRED_COUNT; i++) {
            service.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        //线程使用acquire获取一个许可证
                        semaphore.acquire();
                        System.out.println("通过,save data,线程: "+Thread.currentThread().getName());
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } finally {
                        semaphore.release();
                    }
                }
            });
        }
        service.shutdown();
    }
}
