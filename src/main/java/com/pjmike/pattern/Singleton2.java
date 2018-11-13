package com.pjmike.pattern;

/**
 * 单例模式——单线程版本
 *
 * @author pjmike
 * @create 2018-09-15 16:54
 */
public class Singleton2 {
    private static Singleton2 single ;

    private Singleton2() {

    }

    public static Singleton2 getInstance() {
        if (single == null) {
            synchronized (Singleton.class) {
                if (single == null) {
                    single = new Singleton2();
                }
            }
        }
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
