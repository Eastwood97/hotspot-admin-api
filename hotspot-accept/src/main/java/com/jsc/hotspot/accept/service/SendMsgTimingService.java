package com.jsc.hotspot.accept.service;

import com.alibaba.fastjson.JSON;
import com.jsc.hotspot.accept.facade.KafkaSender;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class SendMsgTimingService {

    private Log log= LogFactory.getLog(SendMsgTimingService.class);
    @Autowired
    private KafkaSender kafkaSender;

    @Scheduled(cron = "0/5 * * * * ?")
    public void sendMsg(){
        log.info("KAFKA准备发送消息");
//        StringBuilder sb = new StringBuilder();
//        sb.append("{");
//        sb.append("\"devId\":").append("123456");
//        sb.append(",\"targetFaceImg\":").append("9527.jpg");
//        sb.append(",\"compareScore\":").append("3.000028");
//        sb.append(",\"captureTime\":").append("2019-08-01 12:20:02");
//        sb.append(",\"captureFaceImg\":").append("10086.jpg");
//        sb.append(",\"libraryName\":").append("3");
//        sb.append("}");
//        String msg = sb.toString();
        Map<String,String> map=new HashMap<String,String>();
        map.put("devId","123456");
        map.put("targetFaceImg","9527.jpg");
        map.put("captureTime","2019-08-01 12:20:02");
        map.put("libraryName","3");
        map.put("captureFaceImg","10086.jpg");
        String msg= JSON.toJSONString(map);
        kafkaSender.send(msg);
        log.info("kafka发送消息："+msg);
    }
}
