package com.pjmike.java8;

import java.util.function.Predicate;

/**
 * @description:
 * @author: pjmike
 * @create: 2019/04/08 22:44
 */
public class PredicateTest {
    public static void main(String[] args) {
        Predicate<String> predicate = s -> s.length() > 0;
        System.out.println(predicate.test("foo"));
    }
}
