package com.pjmike.netty.protocol;

/**
 * @author pjmike
 * @create 2018-09-30 16:17
 */
public enum  SerializerType {
    FAST_JSON((byte) 0x01);
    private final byte value;

    SerializerType(byte value) {
        this.value = value;
    }

    public byte getValue() {
        return value;
    }
}
