package com.jsc.hotspot.db.dao.ext;

import com.jsc.hotspot.db.domain.HotNumTableHandle;

import java.util.List;

/**
 * @author huixing
 * @description
 * @date 2020/3/11
 */
public interface HotNumTableHandleExtMapper {
    /**
     * 批量插入
     * @param list
     * @return
     */
    Integer insertBatch(List<HotNumTableHandle> list);
}
