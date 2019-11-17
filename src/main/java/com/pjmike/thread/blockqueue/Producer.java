package com.pjmike.thread.blockqueue;

import java.util.concurrent.BlockingQueue;

/**
 * @description:
 * @author: pjmike
 * @create: 2019/01/22 22:09
 */
public class Producer implements Runnable{
    private final BlockingQueue<Message> queue;

    public Producer(BlockingQueue queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        //生产消息
        for (int i = 0; i < 10; i++) {
            Message msg = new Message(i + "");
            try {
                Thread.sleep(i);
                queue.put(msg);
                System.out.println("Produced: " + msg.getMsg());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        //退出消息
        Message message = new Message("exit");
        try {
            queue.put(message);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
