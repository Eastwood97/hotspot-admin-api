package com.jsc.hotspot.accept.service;

/**
 * @author huixing
 * @description kafka生产者接口
 * @date 2019/10/29
 */
public interface KafkaSender {

//    @Async
    void send(String msg);

}

