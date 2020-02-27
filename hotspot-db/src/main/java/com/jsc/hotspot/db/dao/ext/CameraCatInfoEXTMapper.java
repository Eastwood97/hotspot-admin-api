package com.jsc.hotspot.db.dao.ext;

import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface CameraCatInfoEXTMapper {
    int deleteById(@Param("ids") String[] ids);
    /**
     *
     * 功能描述: 此处是为了获取15天内的上号数量进行展示
     *
     * @param: List<Date> capture_time
     * @return: List<Map>
     * @auther: ww
     * @date: 2019/11/15 0015 10:25
     */
    List<Map> selectCount(@Param(value = "startTime") Date startTime, @Param(value = "endTime") Date endTime);

    /**
     * 上号数量统计
     * @return
     */
    Map<String, Integer> countByDay();

    /**
     * 上号数量统计
     * @return
     */
    Map<String, Integer> countByMonth();
}