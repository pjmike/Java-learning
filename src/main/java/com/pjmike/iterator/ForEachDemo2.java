package com.pjmike.iterator;

import java.util.ArrayList;
import java.util.List;

/**
 * @description:
 * @author: 13572
 * @create: 2019/05/02 20:23
 */
public class ForEachDemo2 {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        list.add("pj0");
        list.add("pj1");
        list.add("pj2");
        list.add("pj3");
        for (String s: list) {
            if (s.equals("pj0")) {
                list.remove(s);
            }
        }
    }
}
