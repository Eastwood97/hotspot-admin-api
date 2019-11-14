package com.jsc.hotspot.accept.facade;

import org.springframework.boot.autoconfigure.data.redis.RedisProperties;

import java.io.IOException;

/**
 * @author huixing
 * @description 客户端接口
 * @date 2019/11/13
 */
public interface RedisClientInvoker<T> {
    T invoke(RedisProperties.Jedis jedis) throws IOException;
}

