package com.jsc.hotspot.db.dao.ext;

import com.jsc.hotspot.db.domain.HotFrontDevice;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;
/**
 * @author tzm
 * @desc 设备管理数据访问层扩展接口
 */
public interface HotFrontDeviceEXTMapper {

    int deleteById(@Param("devIds") String[] devIds);
    List<HotFrontDevice> selectDeviceList();
    List getregionIdList(@Param("region_id")Integer regionId);
}
