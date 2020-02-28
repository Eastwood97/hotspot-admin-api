package com.jsc.hotspot.db.dao.ext;

import org.apache.ibatis.annotations.Param;
/**
 * @author tzm
 * @desc 人脸识别数据访问层扩展接口
 */
public interface FaceCameraResultEXOMapper {
    int deleteById(@Param("ids") String[] ids);
}
