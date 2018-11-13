package com.pjmike.netty.protocol;

import lombok.Data;

/**
 * 消息
 *
 * @author pjmike
 * @create 2018-09-30 11:40
 */
@Data
public class Request {
    private String requestId;
    private Byte type;
    private String name;
}
