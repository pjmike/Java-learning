package com.pjmike.java8;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * @description:
 * @author: pjmike
 * @create: 2019/04/09 14:52
 */
public class ParallelStreamsTest {
    public static void main(String[] args) {
        int max = 1000000;
        List<String> values = new ArrayList<>();
        for (int i = 0; i < max ; i++) {
            UUID uuid = UUID.randomUUID();
            values.add(uuid.toString());
        }
        //顺序排序
        long t0 = System.nanoTime();
        long count = values.stream()
                .sorted().count();
        long t1 = System.nanoTime();

        long millis = TimeUnit.NANOSECONDS.toMillis(t1 - t0);
        System.out.println(String.format("sequential sort took: %d ms",millis));
        //并行排序
        long t2 = System.nanoTime();
        long count_v2 = values.parallelStream()
                .sorted()
                .count();
        long t3 = System.nanoTime();
        long millis_v2 = TimeUnit.NANOSECONDS.toMillis(t3 - t2);
        System.out.println(String.format("sequential sort took in parallelStream: %d ms",millis_v2));

    }
}
