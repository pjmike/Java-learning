package com.pjmike.concurrency.pool;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.*;

/**
 * @description:
 * @author: pjmike
 * @create: 2019/04/12 22:52
 */
public class HowToGetExecutosServicQuestion {
    public static void main(String[] args) throws InterruptedException {
        // main线程启动子线程，子线程的创建 ThreadFactory
        ExecutorService executorService = Executors.newCachedThreadPool();
        Set<Thread> threads = new HashSet<>();
        setThreadFactory(executorService, threads);
        // ThreadPoolExcutor 有beforeExecute和after能够获取当前线程数，只在这里面有
        for (int i = 0; i < 9; i++) {
            executorService.submit(() -> {
            });
        }
        executorService.awaitTermination(3, TimeUnit.SECONDS);
        System.out.println("线程创造的线程： " + threads);
        threads.stream()
                .filter(Thread::isAlive)
                .forEach(thread -> {
                    System.out.println("线程池创建的线程： "+thread);
                });

        Thread thread = Thread.currentThread();
    }

    private static void setThreadFactory(ExecutorService executorService,Set<Thread> threadContainer) {
        if (executorService instanceof ThreadPoolExecutor) {
            ThreadPoolExecutor threadPoolExecutor = (ThreadPoolExecutor) executorService;
            ThreadFactory threadFactory = threadPoolExecutor.getThreadFactory();
            threadPoolExecutor.setThreadFactory(new DelegatingThreadFactory(threadFactory, threadContainer));
        }
    }

    /**
     * 包装器模式
     */
    private static class DelegatingThreadFactory implements ThreadFactory {
        private  final  ThreadFactory delaegate;
        private final Set<Thread> threadContainer;
        private DelegatingThreadFactory(ThreadFactory delaegate, Set<Thread> threadContainer) {
            this.delaegate = delaegate;
            this.threadContainer = threadContainer;
        }

        @Override
        public Thread newThread(Runnable r) {
            Thread thread = delaegate.newThread(r);
            threadContainer.add(thread);
            return thread;
        }
    }
}
