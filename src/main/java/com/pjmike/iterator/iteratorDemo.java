package com.pjmike.iterator;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @description:
 * @author: 13572
 * @create: 2019/05/02 20:20
 */
public class iteratorDemo {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        list.add("pj0");
        list.add("pj1");
        list.add("pj2");
        list.add("pj3");
        Iterator<String> iterator = list.iterator();
        while (iterator.hasNext()) {
            if (iterator.next().equals("pj0")) {
                iterator.remove();
            }
        }
        System.out.println(list);
    }
}
