package com.pjmike.java8;

/**
 * @description:
 * @author: pjmike
 * @create: 2019/04/08 22:37
 */
@FunctionalInterface
public interface Converter<F,T> {
    T convert(F from);
}
