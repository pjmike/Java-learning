package com.pjmike.atomic;

import java.util.concurrent.atomic.AtomicLong;

/**
 * @description:
 * @author: pjmike
 * @create: 2019/01/22 15:09
 */
public class AtomicTest {
    private AtomicLong atomicLong = new AtomicLong(0);
    public long getAndIncrement() {
        return atomicLong.getAndIncrement();
    }
}
