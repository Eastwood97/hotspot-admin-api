package com.jsc.hotspot.db.dao.ext;

import com.jsc.hotspot.db.domain.Region;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author tzm
 * @desc 区域管理数据访问层扩展接口
 */
public interface RegionEXTMapper {
    /**
     * 批量删除
     * @param ids
     * @return
     */
    int deleteById(@Param("ids") String [] ids);

    List<Region> selectAll();
}
