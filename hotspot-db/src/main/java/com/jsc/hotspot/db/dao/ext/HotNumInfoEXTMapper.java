package com.jsc.hotspot.db.dao.ext;

import com.jsc.hotspot.db.domain.HotNumInfo;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Map;

public interface HotNumInfoEXTMapper {
    /**
     * 根据时间点查询前后30秒的取号记录
     * @author tzm
     * @param captureTime
     * @return
     */
    @Select("select * from hot_num_info where capture_time<DATE_SUB(#{captureTime},INTERVAL 30 SECOND)")
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
    Long selectDistCount();
    List<Map> selectCount(@Param(value = "startTime") Date startTime, @Param(value = "endTime") Date endTime);
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

    List<Map> selectTraffic(@Param(value = "devId") Integer devId, @Param(value = "startTime") Date startTime, @Param(value = "endTime") Date endTime);
    Map selectMyGeGuoCount(@Param(value = "devId") Integer devId, @Param(value = "startTime") Date startTime, @Param(value = "endTime") Date endTime);
    List<Map> selectGuoWaiGeGuoCount(@Param(value = "devId") Integer devId, @Param(value = "startTime") Date startTime, @Param(value = "endTime") Date endTime);
    List<Map> selectGuoBieCount(@Param(value = "devId") Integer devId, @Param(value = "startTime") Date startTime, @Param(value = "endTime") Date endTime);
    Map selectGuojiCount(@Param(value = "devId") Integer devId, @Param(value = "startTime") Date startTime, @Param(value = "endTime") Date endTime);
    Map selectGuojiCounts(@Param(value = "devId") Integer devId, @Param(value = "startTime") Date startTime, @Param(value = "endTime") Date endTime);
    List<Map> selectbansuiLists(@Param(value = "id") Long id, @Param(value = "devId") Long devId, @Param(value = "imsi") String imsi,
                                @Param(value = "createTime") Integer createTime);
    List<Map> selectHotNumInfoList(@Param("dev_id") Long dev_id, @Param("imsi") String imsi, @Param("imei") String imei,
                                   @Param("isdn") String isdn, @Param("capture_time") LocalDateTime capture_time, @Param("page") Integer page, @Param("rows") Integer rows,
                                   @Param("startTime") LocalDateTime startTime,
                                   @Param("endTime") LocalDateTime endTime);
    int countDistinctByIMSI();

    /**
     * IMSI去重统计
     * @param minTime
     * @param maxTime
     * @return
     */
    List<HotNumInfo> selectDistinctImsi(@Param("startTime") LocalDateTime minTime, @Param("endTime") LocalDateTime maxTime);
}
