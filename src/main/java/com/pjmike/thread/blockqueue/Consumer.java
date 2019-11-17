package com.pjmike.thread.blockqueue;

import java.util.concurrent.BlockingQueue;

/**
 * @description:
 * @author: pjmike
 * @create: 2019/01/22 22:11
 */
public class Consumer implements Runnable{
    private final BlockingQueue<Message> queue;

    public Consumer(BlockingQueue queue) {
        this.queue = queue;
    }
    @Override
    public void run() {
            try {
                Message msg;
                while ((msg = queue.take()).getMsg() != "exit") {
                    Thread.sleep(10);
                    System.out.println("Consumer 消费： " + msg.getMsg());
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
    }
}
