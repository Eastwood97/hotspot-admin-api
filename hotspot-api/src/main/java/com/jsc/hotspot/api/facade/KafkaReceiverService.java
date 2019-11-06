package com.jsc.hotspot.api.facade;

//import com.point.common.util.ImageUtil;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.stereotype.Component;

/**
 * @author huixing
 * @description Kafka接收接口
 * @date 2019/10/29
 */
@Component
public interface KafkaReceiverService {

    /**
     * 监听 "picTopic" 将图片数据存入文件系统和数据库
     * @param record
     */
    void listen(ConsumerRecord<?, ?> record);
}
