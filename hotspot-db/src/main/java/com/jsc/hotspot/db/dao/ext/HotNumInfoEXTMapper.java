package com.jsc.hotspot.db.dao.ext;

import com.jsc.hotspot.db.domain.HotNumInfo;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Map;

public interface HotNumInfoEXTMapper {
    @Select("select * from hot_num_info where create_time<DATE_SUB(#{captureTime},INTERVAL 30 SECOND)")
    List<HotNumInfo> getIntervalNum(LocalDateTime captureTime);
    /**
     *
     * 功能描述: 此处是为了获取15天内的上号数量进行展示
     *
     * @param: List<Date> capture_time
     * @return: List<Map>
     * @auther: ww
     * @date: 2019/11/15 0015 10:25
     */
    List<Map> selectCount(List<Date> capture_time);
    /**
     *
     * 功能描述: 归属地进行分组统计
     *
     * @param:
     * @return:
     * @auther: ww
     * @date: 2019/11/15 0015 10:26
     */
    List<Map> selectGuiShuDiList();

    List<Map> selectTraffic(@Param(value ="devId") Integer devId, @Param(value ="startTime") Date startTime, @Param(value ="endTime") Date endTime);
    Map selectMyGeGuoCount(@Param(value ="devId") Integer devId, @Param(value ="startTime") Date startTime, @Param(value ="endTime") Date endTime);
    List<Map> selectGuoWaiGeGuoCount(@Param(value ="devId") Integer devId,  @Param(value ="startTime") Date startTime, @Param(value ="endTime") Date endTime);
    List<Map> selectGuoBieCount(@Param(value ="devId") Integer devId, @Param(value ="startTime") Date startTime, @Param(value ="endTime") Date endTime);
    Map selectGuojiCount(@Param(value ="devId") Integer devId, @Param(value ="startTime") Date startTime, @Param(value ="endTime") Date endTime);
    Map selectGuojiCounts(@Param(value ="devId") Integer devId, @Param(value ="startTime") Date startTime, @Param(value ="endTime") Date endTime);

}
