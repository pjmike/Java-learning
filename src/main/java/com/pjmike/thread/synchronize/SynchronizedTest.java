package com.pjmike.thread.synchronize;

/**
 * @description:
 * @author: pjmike
 * @create: 2019/01/21 19:51
 */
public class SynchronizedTest {

    public synchronized void test1() {

    }

    public void test2() {
        synchronized (this) {

        }
    }
}
