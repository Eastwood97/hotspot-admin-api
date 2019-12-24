package com.jsc.hotspot.db.dao;

import com.jsc.hotspot.db.domain.HotFrontDeviceOption;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Auther: WW
 * @Date: 2019/12/23 0023 15:57
 * @Description:
 */
public interface HotFrontDeviceOptionMapper {
    List<HotFrontDeviceOption> selectHotFrontDeviceOption(@Param("id") Integer id);
}
