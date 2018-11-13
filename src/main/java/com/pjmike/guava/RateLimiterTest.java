package com.pjmike.guava;

import com.google.common.util.concurrent.RateLimiter;

import java.time.LocalDateTime;

/**
 * @author pjmike
 * @create 2018-09-13 20:15
 */
public class RateLimiterTest implements Runnable{
    /**
     * 令牌桶算法
     * 每秒产生 2 个令牌
     *
     */
    private static final RateLimiter limiter = RateLimiter.create(2);

    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 5; i++) {
            RateLimiterTest rateLimiterTest = new RateLimiterTest();
            new Thread(rateLimiterTest).start();
        }
    }
    @Override
    public void run() {
        final double acquire = limiter.acquire(1);
        System.out.println("当前时间 - "+LocalDateTime.now()+" - "+Thread.currentThread().getName()+"- 阻塞时长 -"+ acquire+ "秒");
    }
}
