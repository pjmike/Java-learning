package com.pjmike.java8;

/**
 * @description:
 * @author: pjmike
 * @create: 2019/04/08 22:07
 */
public class FunctionalInterfaceMain {
    public static void main(String[] args) {
        FunctionalInterfaceTest test = (s1, s2) -> s1.equals(s2);
        System.out.println(test.equals("s1", "s2"));
    }
}
