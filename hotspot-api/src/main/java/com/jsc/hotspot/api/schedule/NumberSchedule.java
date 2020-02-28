package com.jsc.hotspot.api.schedule;

import com.jsc.hotspot.db.dao.HotNumInfoMapper;
import com.jsc.hotspot.db.dao.ext.HotNumInfoEXTMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SetOperations;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @author huixing
 * @description 定时任务
 * @date 2020/2/20
 */
@Component
public class NumberSchedule {

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Autowired
    private HotNumInfoEXTMapper hotNumInfoEXTMapper;

    /**
     * 零点定时清理redis，将前一天数据清零，查询之前的总数，
     */
    @Scheduled(cron = "0 0 0 * * *")
    public void clearRedisAndUpdateData(){
        ValueOperations valueOperations = redisTemplate.opsForValue();
        SetOperations<String, String> numbers = redisTemplate.opsForSet();
        redisTemplate.opsForSet().remove("numbers");
        // 查询总数
        Integer allTotal = hotNumInfoEXTMapper.countDistinctByIMSI();
        valueOperations.set("allTotal", allTotal);
    }

}
