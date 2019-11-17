package com.pjmike.iterator;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @description:
 * @author: pjmike
 * @create: 2019/03/13 20:42
 */
public class ForEachDemo {
    public static void main(String[] args) {
        List<Integer> userNames = new ArrayList<Integer>(){
            {
                add(123);
                add(12);
                add(1);
                add(2);
            }
        };
        Iterator iterator = userNames.iterator();
        while (iterator.hasNext()) {
            Integer c = (Integer) iterator.next();
            if (c == 2) {
                iterator.remove();
            }
        }
        System.out.println(userNames);
    }
}
