package com.jsc.hotspot.accept.facade.impl;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.jsc.hotspot.accept.facade.KafkaSender;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

/**
 * @author huixing
 * @description Kafka生产者实现
 * @date 2019/11/4
 */
@Service
@Slf4j
public class KafkaSenderServiceImpl implements KafkaSender {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    private Gson gson = new GsonBuilder().create();

    //发送消息方法
//    @Async
    public void send(String msg) {
        log.info("+++++++++++++++++++++  message = {}", msg);

        ListenableFuture<SendResult<String, String>> future = kafkaTemplate.send("picTopic", msg);

        future.addCallback(new ListenableFutureCallback<SendResult<String, String>>() {
            @Override
            public void onFailure(Throwable throwable) {
                log.info("照片数据放入kafka失败");
            }

            @Override
            public void onSuccess(SendResult<String, String> stringStringSendResult) {
                log.info("照片数据放入kafka成功");
            }
        });
    }
}
