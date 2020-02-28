package com.jsc.hotspot.api.schedule;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @author huixing
 * @description 统计定时任务
 * @date 2020/1/19
 */
@Component
public class StatisticsSchedule {

    /**
     * 每日定时统计imsi数量
     */
    @Scheduled(cron = "0 0 0 * * *")
    public void totalNumberPerDay(){

    }

    /**
     * 每日定时统计归属地
     */
    @Scheduled(cron = "0 0 0 * * *")
    public void totalDailyHomeStatistics(){
        // 国内数量 国外数量
    }
}
