package com.jsc.hotspot.accept.facade.impl;

import com.jsc.hotspot.accept.facade.RedisSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

/**
 * @author huixing
 * @description 消息生产者
 * @date 2019/11/13
 */
@Service
public class RedisSenderImpl implements RedisSender{

    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    //向通道发送消息的方法
    public void sendChannelMess(String channel, String message) {
        stringRedisTemplate.convertAndSend(channel, message);
    }

}
