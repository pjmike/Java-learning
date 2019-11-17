package com.pjmike.java8;

import java.util.HashMap;
import java.util.Map;

/**
 * @description:
 * @author: pjmike
 * @create: 2019/04/09 15:02
 */
public class Map_Java8 {
    public static void main(String[] args) {
        Map<Integer, String> map = new HashMap<>();
        for (int i = 0; i < 10; i++) {
            map.putIfAbsent(i, "val" + i);
        }
        map.forEach((integer, s) -> System.out.println(s));
    }
}
