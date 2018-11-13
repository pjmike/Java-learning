package com.pjmike.thread;

/**
 * @author pjmike
 * @create 2018-11-12 17:56
 */
public class VolatileTest {
    volatile long vl = 0L;

    public long getVl() {
        return vl;
    }

    public void setVl(long vl) {
        this.vl = vl;
    }

    public void getAndIncrement() {
        vl++;
    }

    public static void main(String[] args) {
        final VolatileTest volatileTest = new VolatileTest();
        long result = volatileTest.getVl();
        System.out.println(result);
    }
}
