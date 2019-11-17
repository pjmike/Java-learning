package com.pjmike.collections;

import java.util.concurrent.ConcurrentHashMap;

/**
 * @description:
 * @author: pjmike
 * @create: 2019/01/23 11:15
 */
public class ConcurrentHashMapTest {
    public static void main(String[] args) {
        final ConcurrentHashMap<String, Integer> map = new ConcurrentHashMap<>();
        map.put("pjmike", 21);
        Integer age = map.get("pjmike");
        System.out.println(age);
    }
}
