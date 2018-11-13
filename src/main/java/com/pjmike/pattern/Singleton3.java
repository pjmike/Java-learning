package com.pjmike.pattern;

/**
 * 单例模式
 *
 * @author pjmike
 * @create 2018-09-15 16:54
 */
public class Singleton3 {
    private static class SingletonHolder {
        private static final Singleton3 INSTNCE = new Singleton3();
    }

    private Singleton3() {

    }

    public static final Singleton3 getInstance() {
        return SingletonHolder.INSTNCE;
    }
}
