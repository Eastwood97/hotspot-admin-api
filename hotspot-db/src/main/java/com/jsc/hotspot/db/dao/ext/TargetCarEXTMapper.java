package com.jsc.hotspot.db.dao.ext;

import org.apache.ibatis.annotations.Param;

/**
 * @author tzm
 * @desc 目标车牌数据访问层扩展接口
 */
public interface TargetCarEXTMapper {
    /**
     * 批量删除
     * @param targetIds
     * @return
     */
    int deleteById(@Param("targetIds") String [] targetIds);
}
