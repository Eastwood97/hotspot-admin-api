package com.jsc.hotspot.accept.facade;

/**
 * @author huixing
 * @description Redis接收者
 * @date 2019/11/11
 */
public interface RedisReceiver {

    /**
     * 消息接收程序
     */
    void receiveMessage(String message);
}
