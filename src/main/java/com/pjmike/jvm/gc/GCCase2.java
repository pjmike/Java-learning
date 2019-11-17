package com.pjmike.jvm.gc;

/**
 * @description:
 * @author: pjmike
 * @create: 2019/04/03 19:31
 */
public class GCCase2 {
    public static void main(String[] args) {
        while (true) {
            allocate_1M();
        }
    }

    public static void allocate_1M() {
        byte[] data = new byte[1024 * 1024];
    }
}
