package com.pjmike.collections;

import java.util.HashMap;
import java.util.Set;

/**
 * @author pjmike
 * @create 2018-09-03 22:34
 */
public class HashMapTest {
    public static void main(String[] args) {
        HashMap<String, Integer> hashMap = new HashMap<>();
        hashMap.put("张三", 1);
        hashMap.put("李四", 2);
        hashMap.put("王五", 3);
        for (String key : hashMap.keySet()) {
            System.out.println("name: "+key);
        }
    }
}
