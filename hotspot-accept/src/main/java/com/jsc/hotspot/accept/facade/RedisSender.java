package com.jsc.hotspot.accept.facade;

/**
 * @author huixing
 * @description 消息生产者
 * @date 2019/11/13
 */
public interface RedisSender {

    /**
     * 向通道发送消息的方法
     * @param channel
     * @param message
     */
    void sendChannelMess(String channel, String message);

}
