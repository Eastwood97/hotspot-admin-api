package com.jsc.hotspot.accept.facade.impl;

import com.jsc.hotspot.accept.facade.RedisReceiver;
import org.springframework.stereotype.Service;

/**
 * @author huixing
 * @description 消息接收者
 * @date 2019/11/13
 */
@Service
public class RedisReceiverImpl implements RedisReceiver {

    public void receiveMessage(String message) {
        System.out.println(message);
    }

}
