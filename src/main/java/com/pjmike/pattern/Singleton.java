package com.pjmike.pattern;

/**
 * 单例模式——单线程版本
 *
 * @author pjmike
 * @create 2018-09-15 16:54
 */
public class Singleton {
    private static final Singleton single = new Singleton();

    private Singleton() {

    }

    public static Singleton getInstance() {
        return single;
    }
//
    //    public static Singleton getInstance() {
//        if (single == null) {
//            single = new Singleton();
//        }
//        return single;
//    }
//    public static synchronized Singleton getInstance() {
//        if (single == null) {
//            single = new Singleton();
//        }
//        return single;
//    }
}
