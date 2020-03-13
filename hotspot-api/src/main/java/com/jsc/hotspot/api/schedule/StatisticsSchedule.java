package com.jsc.hotspot.api.schedule;

import com.jsc.hotspot.db.dao.HotNumDayCountMapper;
import com.jsc.hotspot.db.dao.HotNumInfoMapper;
import com.jsc.hotspot.db.dao.HotNumTableHandleMapper;
import com.jsc.hotspot.db.dao.ext.HotNumAttrPreHandleExtMapper;
import com.jsc.hotspot.db.dao.ext.HotNumDayCountExtMapper;
import com.jsc.hotspot.db.dao.ext.HotNumInfoEXTMapper;
import com.jsc.hotspot.db.dao.ext.HotNumTableHandleExtMapper;
import com.jsc.hotspot.db.domain.*;
import org.apache.tomcat.jni.Local;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author huixing
 * @description 统计定时任务
 * @date 2020/1/19
 */
@Component
public class StatisticsSchedule {

    @Autowired
    private HotNumInfoEXTMapper hotNumInfoMapper;

    @Autowired
    private HotNumTableHandleExtMapper hotNumTableHandleExtMapper;

    @Autowired
    private HotNumAttrPreHandleExtMapper hotNumAttrPreHandleExtMapper;

    @Autowired
    private HotNumDayCountExtMapper hotNumDayCountExtMapper;
    /**
     * 每日定时做预处理
     */
    @Scheduled(cron = "0 0 1 * * ?")
    public void totalNumberPerDay(){
        HotNumInfoExample hotNumInfoExample = new HotNumInfoExample();
        HotNumInfoExample.Criteria criteria = hotNumInfoExample.createCriteria();
        LocalDateTime minTime = LocalDateTime.MIN;
        LocalDateTime maxTime = LocalDateTime.MAX;
        criteria.andCaptureTimeBetween(minTime, maxTime);

        List<HotNumInfo> hotNumInfos = hotNumInfoMapper.selectDistinctImsi(minTime, maxTime);
        List<HotNumTableHandle> hotNumTableHandles = new ArrayList<>();
        hotNumInfos.forEach((x) -> {
            HotNumTableHandle hotNumTableHandle = new HotNumTableHandle();
            BeanUtils.copyProperties(x, hotNumTableHandle);
            hotNumTableHandles.add(hotNumTableHandle);
        });
        if (hotNumTableHandles.size() > 0) {
            Integer num = hotNumTableHandleExtMapper.insertBatch(hotNumTableHandles);
            // 取出的号码做归属地分析
            LocalDateTime localDate = LocalDateTime.now();

            Map<Long, Map<String, Long>> countDes = new HashMap<>();

            for (HotNumInfo hotNumInfo : hotNumInfos){
                if (countDes.containsKey(hotNumInfo.getDevId())){
                    Map<String, Long> map = countDes.get(hotNumInfo.getDevId());
                    if (map != null){
                        if (map.containsKey(hotNumInfo.getAttribution())){
                            map.put(hotNumInfo.getAttribution(), map.get(hotNumInfo.getAttribution() + 1));

                        }
                    }
                }
            }

            List<HotNumAttrPreHandle> hotNumAttrPreHandles = new ArrayList<>();
            HotNumAttrPreHandle hotNumAttrPreHandle = null;
            List<HotNumDayCount> hotNumDayCounts = new ArrayList<>();
            HotNumDayCount hotNumDayCount = null;

            for (Map.Entry<Long, Map<String, Long>> entry : countDes.entrySet()) {
                hotNumDayCount = new HotNumDayCount();
                hotNumDayCount.setImsiCount(new Long(entry.getValue().size()));
                hotNumDayCount.setCaptureDate(localDate);
                hotNumDayCount.setDevId(entry.getKey());
                hotNumDayCounts.add(hotNumDayCount);
                for (Map.Entry<String, Long> entry1 : entry.getValue().entrySet()){
                    hotNumAttrPreHandle = new HotNumAttrPreHandle();
                    hotNumAttrPreHandle.setAttribution(entry1.getKey());
                    hotNumAttrPreHandle.setAttributionNum(entry1.getValue());
                    hotNumAttrPreHandle.setCaptureDate(localDate);
                    hotNumAttrPreHandle.setDevId(entry.getKey());
                    if (hotNumAttrPreHandle.getAttribution().startsWith("中国")){
                        hotNumAttrPreHandle.setCountry("中国");
                    }else {
                        hotNumAttrPreHandle.setCountry(hotNumAttrPreHandle.getAttribution());
                    }
                    hotNumAttrPreHandles.add(hotNumAttrPreHandle);
                }
            }
            if (hotNumAttrPreHandles.size() > 0) {
                hotNumAttrPreHandleExtMapper.insertBatch(hotNumAttrPreHandles);
            }            // 取出的号码做人流分析
            if (hotNumDayCounts.size() > 0) {
                hotNumDayCountExtMapper.insertBatch(hotNumDayCounts);
            }
        }
    }

    /**
     * 每日定时统计归属地
     */
    @Scheduled(cron = "0 0 0 * * *")
    public void totalDailyHomeStatistics(){
        // 国内数量 国外数量
    }
}
