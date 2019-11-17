package com.pjmike.thread.volatile_;

/**
 * @author pjmike
 * @create 2018-11-16 20:33
 */
public class VolatileTest2 {
    int a;
    volatile int v1 = 1;
    volatile int v2 = 2;

    void readAndWrite() {
        int i = v1;     //第一个volatile 读
        int j = v2;     //第二个volatile 读
        a = i + j;      //普通写
        v1 = i + 1;     //第一个volatile 写
        v2 = j * 2;     //第二个volatile 写
    }
}
