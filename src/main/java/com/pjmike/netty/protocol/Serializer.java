package com.pjmike.netty.protocol;

public interface Serializer {
    /**
     * 获取序列化算法
     * @return
     */
    byte getSerializerAlgorithm();

    /**
     * 序列化 (对象 -> 字节数组)
     *
     * @param t
     * @param <T>
     * @return
     */
    <T> byte[] serialize(T t);

    /**
     * 反序列化 (字节数组 -> 对象)
     *
     * @param clazz
     * @param bytes
     * @param <T>
     * @return
     */
    <T> T deserialize(Class<T> clazz, byte[] bytes);
}
