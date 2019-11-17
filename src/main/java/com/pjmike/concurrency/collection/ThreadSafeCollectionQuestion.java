package com.pjmike.concurrency.collection;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @description:
 * @author: pjmike
 * @create: 2019/04/07 22:46
 */
public class ThreadSafeCollectionQuestion {
    public static void main(String[] args) {
        List<Integer> list = Arrays.asList(1,2, 3, 4, 5);
        //java 9 的实现方式
//        Set<Integer> set = Set.of(1, 2, 4);
        Collections.synchronizedList(list);
        list = new CopyOnWriteArrayList<>(list);
    }
}
