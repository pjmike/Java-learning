package com.pjmike.jvm.gc;


/**
 * @author pjmike
 * @create 2018-09-09 11:19
 */
public class GCCase {
    public static void allocate_1M() {
        byte[] _1M = new byte[1024 * 1000];
    }

    public static void main(String[] args) {
        System.out.println("-Xmx:"+Runtime.getRuntime().maxMemory()/1024/1024+"M");
//        for (int i = 0; i < 1000; i++) {
//            allocate_1M();
//        }
        allocate_1M();
        System.gc();
    }
}
