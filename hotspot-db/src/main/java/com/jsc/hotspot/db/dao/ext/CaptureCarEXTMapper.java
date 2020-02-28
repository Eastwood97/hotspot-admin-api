package com.jsc.hotspot.db.dao.ext;

import org.apache.ibatis.annotations.Param;
/**
 * @author tzm
 * @desc 抓拍车牌数据访问层扩展接口
 */
public interface CaptureCarEXTMapper {
    int deleteById(@Param("ids") String [] ids);
}
