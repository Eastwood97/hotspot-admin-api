package com.jsc.hotspot.db.dao.ext;

import com.jsc.hotspot.db.po.CampareValue;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface RelatedNumEXTMapper {
    @Select({"select imsi,count(imsi) count from related_num where file_name=#{filename} group by imsi order by count desc limit 3"})
    List<CampareValue> getCampareValue(String filename);
}
