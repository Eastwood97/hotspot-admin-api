package com.jsc.hotspot.api.facade;

//import com.point.common.util.ImageUtil;

import org.apache.kafka.clients.consumer.ConsumerRecord;

import java.lang.reflect.InvocationTargetException;

/**
 * @author huixing
 * @description Kafka接收接口
 * @date 2019/10/29
 */
public interface KafkaReceiverService {

    /**
     * 监听 "picTopic" 将图片数据存入文件系统和数据库
     * @param record
     */
    void listen(ConsumerRecord<?, ?> record) throws InvocationTargetException, IllegalAccessException;
}
