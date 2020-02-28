package com.jsc.hotspot.db.dao.ext;

import org.apache.ibatis.annotations.Param;

/**
 * @author tzm
 * @desc 抓拍车牌数据访问层扩展接口
 */
public interface PlateNumberCompareEXTMapper {
    /**
     * 批量删除
     * @param ids
     * @return
     */
    int deleteById(@Param("ids") String [] ids);
}
