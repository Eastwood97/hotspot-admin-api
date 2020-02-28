package com.jsc.hotspot.db.dao.ext;

import org.apache.ibatis.annotations.Param;

/**
 * @author tzm
 * @desc 关联号码结果数据访问层扩展接口
 */
public interface RelatedNumResultEXTMapper {
    /**
     * 批量删除
     * @param ids
     * @return
     */
    int deleteById(@Param("ids") String [] ids);
}
