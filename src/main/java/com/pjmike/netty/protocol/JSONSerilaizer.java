package com.pjmike.netty.protocol;

import com.alibaba.fastjson.JSON;

/**
 * @author pjmike
 * @create 2018-09-30 15:00
 */
public class JSONSerilaizer implements Serializer{
    @Override
    public byte getSerializerAlgorithm() {
        return SerializerType.FAST_JSON.getValue();
    }

    @Override
    public <T> byte[] serialize(T t) {
        return JSON.toJSONBytes(t);
    }

    @Override
    public <T> T deserialize(Class<T> clazz, byte[] bytes) {
        return JSON.parseObject(bytes, clazz);
    }
}
