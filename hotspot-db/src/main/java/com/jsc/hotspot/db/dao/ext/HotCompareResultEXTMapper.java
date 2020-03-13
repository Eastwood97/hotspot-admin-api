package com.jsc.hotspot.db.dao.ext;

import com.jsc.hotspot.db.domain.HotCompareResult;
import com.jsc.hotspot.db.domain.HotCompareResultExample;
import com.jsc.hotspot.db.entity.HotCompareResultList;
import org.apache.ibatis.annotations.Param;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @Auther: WW
 * @Date: 2019/12/30 0030 11:28
 * @Description:
 */
public interface HotCompareResultEXTMapper {
    List selectHotCompareResult(@Param("imsi") String imsi, @Param("imei") String imei, @Param("page") Integer page, @Param("rows") Integer rows,
                                @Param("startTime") LocalDateTime startTime,
                                @Param("endTime") LocalDateTime endTime);
}
