package com.pjmike.java8;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * @description:
 * @author: pjmike
 * @create: 2019/04/08 21:50
 */
public class LambdaTest {
    public static void main(String[] args) {
        List<Integer> list = Arrays.asList(1, 3, 28, 6, 4);
        Collections.sort(list, Integer::compareTo);
        System.out.println(list);
    }
}
