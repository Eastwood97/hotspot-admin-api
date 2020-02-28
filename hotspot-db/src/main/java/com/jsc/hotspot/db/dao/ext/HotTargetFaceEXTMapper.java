package com.jsc.hotspot.db.dao.ext;

import com.jsc.hotspot.db.domain.CameraTargetFace;
import com.jsc.hotspot.db.domain.CameraTargetFaceExample;
import com.jsc.hotspot.db.entity.TargetFaceResult;
import org.apache.ibatis.annotations.Param;

import java.util.List;
/**
 * @author tzm
 * @desc 目标人脸数据访问层扩展接口
 */
public interface HotTargetFaceEXTMapper {
    /**
     * 批量删除
     * @param targetIds
     * @return
     */
    int deleteById(@Param("targetIds") String [] targetIds);

    List<TargetFaceResult> selectByMyExampleSelective(@Param("example") CameraTargetFaceExample example, @Param("selective") CameraTargetFace.Column ... selective);
}
