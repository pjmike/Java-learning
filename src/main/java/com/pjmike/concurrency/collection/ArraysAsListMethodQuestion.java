package com.pjmike.concurrency.collection;

import java.util.Arrays;
import java.util.List;

/**
 * @description:
 * @author: pjmike
 * @create: 2019/04/07 22:55
 */
public class ArraysAsListMethodQuestion {
    public static void main(String[] args) {
        List<Integer> list = Arrays.asList(1, 3, 4, 5, 6);
        list.set(2, 7);
        list.forEach(System.out::println);
        // java 5+: CopyOnWriteArrayList
    }
}
