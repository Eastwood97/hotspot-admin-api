package com.jsc.hotspot.db.dao.ext;

import com.jsc.hotspot.db.domain.HotNumAttrPreHandle;
import com.jsc.hotspot.db.domain.HotNumAttrPreHandleExample;
import org.apache.ibatis.annotations.Param;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

public interface HotNumAttrPreHandleExtMapper {
    Integer insertBatch(List<HotNumAttrPreHandle> list);
}