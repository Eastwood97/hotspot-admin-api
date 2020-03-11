package com.jsc.hotspot.db.dao.ext;

import com.jsc.hotspot.db.po.RealatedNumAndCount;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author tzm
 * @desc 关联号码数据访问层扩展接口
 */
public interface RelatedNumEXTMapper {
    /**
     * 查出归属目标人脸下的imsi，并取出其中数量最多的三个
     * @param targetName
     * @return
     */
    @Select({"select imsi,count(imsi) count from related_num where target_name=#{targetName} group by imsi order by count desc limit 10"})
    List<RealatedNumAndCount> getCampareValue(String targetName);
}
