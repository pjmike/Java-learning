package com.pjmike.thread.blockqueue;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * @description:
 * @author: pjmike
 * @create: 2019/01/22 22:13
 */
public class Setup {
    public static void main(String[] args) {
        BlockingQueue<Message> queue = new ArrayBlockingQueue(16,true);
        Producer p = new Producer(queue);
        Consumer c1 = new Consumer(queue);
        Consumer c2 = new Consumer(queue);
        new Thread(p).start();
        new Thread(c1).start();
    }
}
