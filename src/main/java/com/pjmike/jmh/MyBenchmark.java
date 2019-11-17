package com.pjmike.jmh;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import java.util.stream.LongStream;

/**
 * @description:
 * @author: 13572
 * @create: 2019/06/28 17:50
 */
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MICROSECONDS)
@State(Scope.Thread)
public class MyBenchmark {

    @Benchmark
    public void testMethod_1() {

        long result = LongStream.rangeClosed(1, 10_000_000)
                .reduce(0L, Long::sum);
    }
    @Benchmark
    public void testMethod_2() {
        long result = LongStream.rangeClosed(1, 10_000_000)
                .parallel()
                .reduce(0L, Long::sum);
    }


    public static void main(String[] args) throws RunnerException {
        Options opt = new OptionsBuilder()
                .include(MyBenchmark.class.getSimpleName())
                .forks(1)
                .warmupIterations(5)
                .measurementIterations(5)
                .build();
        new Runner(opt).run();
    }
}
