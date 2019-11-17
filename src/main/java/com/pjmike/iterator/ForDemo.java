package com.pjmike.iterator;

import java.util.ArrayList;
import java.util.List;

/**
 * @description:
 * @author: 13572
 * @create: 2019/05/02 20:18
 */
public class ForDemo {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        list.add("pj0");
        list.add("pj1");
        list.add("pj2");
        list.add("pj3");
        for (int i = 0; i <list.size() ; i++) {
            if (list.get(i).equals("pj0")) {
                list.remove(i);
            }
        }
        System.out.println(list);
    }
}
