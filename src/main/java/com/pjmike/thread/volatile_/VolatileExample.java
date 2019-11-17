package com.pjmike.thread.volatile_;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author pjmike
 * @create 2018-11-12 16:39
 */
public class VolatileExample {

    private Lock lock = new ReentrantLock();
    private int number = 0;

    public int getNumber(){
        return this.number;
    }

    public void increase(){
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        lock.lock();
        try {
            this.number++;
        } finally {
            lock.unlock();
        }
    }

    /**
     * @param args
     */
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        final VolatileExample volDemo = new VolatileExample();
        for(int i = 0 ; i < 500 ; i++){
            new Thread(new Runnable() {

                @Override
                public void run() {
                    volDemo.increase();
                }
            }).start();
        }

        //Èç¹û»¹ÓÐ×ÓÏß³ÌÔÚÔËÐÐ£¬Ö÷Ïß³Ì¾ÍÈÃ³öCPU×ÊÔ´£¬
        //Ö±µ½ËùÓÐµÄ×ÓÏß³Ì¶¼ÔËÐÐÍêÁË£¬Ö÷Ïß³ÌÔÙ¼ÌÐøÍùÏÂÖ´ÐÐ
        while(Thread.activeCount() > 1){
            Thread.yield();
        }

        System.out.println("number : " + volDemo.getNumber());
    }

}
