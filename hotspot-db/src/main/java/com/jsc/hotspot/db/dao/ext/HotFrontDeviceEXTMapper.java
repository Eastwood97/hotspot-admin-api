package com.jsc.hotspot.db.dao.ext;

import com.jsc.hotspot.db.domain.HotFrontDevice;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface HotFrontDeviceEXTMapper {

    int deleteById(@Param("devIds") String[] devIds);
    List<HotFrontDevice> selectDeviceList();
}
