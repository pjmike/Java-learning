package com.pjmike.java8;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

/**
 * @description:
 * @author: pjmike
 * @create: 2019/04/09 14:23
 */
public class StreamTest {
    public static void main(String[] args) {
        List<String> stringCollection = new ArrayList<>();
        stringCollection.add("ddd2");
        stringCollection.add("aaa2");
        stringCollection.add("bbb1");
        stringCollection.add("aaa1");
        stringCollection.add("bbb3");
        stringCollection.add("ccc");
        stringCollection.add("bbb2");
        stringCollection.add("ddd1");
        stringCollection.stream()
                .filter(s -> s.startsWith("a"))
                .forEach(System.out::println);
        stringCollection.stream()
                .sorted()
                .filter(s -> s.startsWith("b"))
                .forEach(System.out::println);
//        System.out.println(stringCollection);
        stringCollection.stream()
                .map(String::toUpperCase)
                .sorted()
                .forEach(System.out::println);
        boolean anyStartsWithA = stringCollection.stream()
                .anyMatch(s -> s.startsWith("a"));
        System.out.println(anyStartsWithA);
        boolean allStartsWithA = stringCollection.stream()
                .allMatch(s -> s.startsWith("a"));
        System.out.println(allStartsWithA);

        long startWithB = stringCollection.stream()
                .filter(s -> s.startsWith("b"))
                .count();
        System.out.println(startWithB);
        Optional<String> reduced = stringCollection.stream()
                .sorted()
                .reduce((s, s2) -> s + "#" + s2);
        reduced.ifPresent(System.out::println);
    }
}
