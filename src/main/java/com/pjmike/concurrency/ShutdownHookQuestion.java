package com.pjmike.concurrency;

/**
 * @description:
 * @author: pjmike
 * @create: 2019/04/07 22:16
 */
public class ShutdownHookQuestion {
    public static void main(String[] args) {
        Runtime runtime = Runtime.getRuntime();
        runtime.addShutdownHook(new Thread(ShutdownHookQuestion::action, "Shutdown Hook Question"));

    }
    private static void action() {
        System.out.printf("线程[%s]正在执行。。", Thread.currentThread().getName());
    }
}
