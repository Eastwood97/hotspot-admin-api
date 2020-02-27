package com.jsc.hotspot.accept.schedule;

import com.alibaba.fastjson.JSONObject;
import com.jsc.hotspot.accept.config.WebSocket;
import com.jsc.hotspot.accept.service.CameraCatInfoService;
import com.jsc.hotspot.accept.service.HoTnumInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

/**
 * @Auther: WW
 * @Date: 2020/2/26 0026 15:20
 * @Description: 15天推送数据
 */
@Component
public class FifteenDayList {
    private WebSocket websocket = new WebSocket();
    @Autowired
    private HoTnumInfoService hoTnumInfoService;
    @Autowired
    private CameraCatInfoService cameraCatInfoService;

    /**
     * 零点定时推送15天的统计数量
     */
    @Scheduled(cron = "0 0 0 * * *")
    public void clearRedisAndUpdateData() {
        //取号15天数量
        List<Map> hoTnumInfoDateNum = hoTnumInfoService.getHoTnumInfoDateNum();
        List<Map> hoTnumInfoDateNumList = getList(hoTnumInfoDateNum);

        //人脸折线图
        List<Map> cameraCatInfoServiceHoTnumInfoDateNum = cameraCatInfoService.getHoTnumInfoDateNum();
        List<Map> cameraCatInfoDateNumList = getList(cameraCatInfoServiceHoTnumInfoDateNum);
        Map<Object, Object> map = new HashMap<>();
        map.put("type", "10");
        map.put("hoTnumInfoDateNum", hoTnumInfoDateNumList);
        map.put("cameraCatDateNum", cameraCatInfoDateNumList);
        String hotNumInfoJson = JSONObject.toJSONString(map);
        websocket.sendAllMessage(hotNumInfoJson);
    }

    //过滤封装15天
    public List<Map> getList(List<Map> hoTnumInfoDateNum) {
        Map map = new HashMap();
        List<Map> maps = new ArrayList<>();
        for (int i = 0; i <= 15; i++) {
            LocalDateTime captureTime = (LocalDateTime) hoTnumInfoDateNum.get(0).get("capture_time2");
            String captureTimeFormat = captureTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            LocalDate localDate = LocalDate.now().minusDays(i);
            String localDateFormat = localDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            if (localDateFormat.equals(captureTimeFormat)) {
                map.put("capture_time", captureTimeFormat);
                map.put("num", hoTnumInfoDateNum.get(i).get("num"));
            } else {
                map.put("capture_time", localDateFormat);
                map.put("num", 0);
            }
        }
        maps.add(map);
        return maps;
    }
}
