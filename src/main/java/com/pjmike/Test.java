package com.pjmike;

import org.springframework.beans.factory.support.DefaultListableBeanFactory;

/**
 * @author pjmike
 * @create 2018-11-21 10:31
 */
public class Test {
    public static void main(String[] args) {
        int result = add(1, 3);
        System.out.printf("result=%d",result);
    }
    public static int add(int a, int b) {
        return a + b;
    }
}
