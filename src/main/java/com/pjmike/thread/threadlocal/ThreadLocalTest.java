package com.pjmike.thread.threadlocal;

/**
 * @description:
 * @author: pjmike
 * @create: 2019/01/22 17:28
 */
public class ThreadLocalTest {
    private static ThreadLocal<Integer> count = ThreadLocal.withInitial(() -> 0);

    public int nextCount() {
        count.set(count.get() + 1);
        return count.get();
    }

    public static void main(String[] args) {
        ThreadLocalTest seqCount = new ThreadLocalTest();
        SeqThread thread1 = new SeqThread(seqCount);
        SeqThread thread2 = new SeqThread(seqCount);
        SeqThread thread3 = new SeqThread(seqCount);
        SeqThread thread4 = new SeqThread(seqCount);

        thread1.start();
        thread2.start();
        thread3.start();
        thread4.start();
    }
    private static class SeqThread extends Thread {
        private ThreadLocalTest seqCount;

        SeqThread(ThreadLocalTest seqCount) {
            this.seqCount = seqCount;
        }

        @Override
        public void run() {
            for (int i = 0; i < 3; i++) {
                System.out.println(Thread.currentThread().getName() + " seqCount :" + seqCount.nextCount());
            }
        }
    }
}
