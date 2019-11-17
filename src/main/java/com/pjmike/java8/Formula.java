package com.pjmike.java8;

/**
 * @description:
 * @author: pjmike
 * @create: 2019/04/08 21:45
 */
public interface Formula {
    double calculate(int a);

    default double sqrt(int a) {
        return Math.sqrt(a);
    }
}
