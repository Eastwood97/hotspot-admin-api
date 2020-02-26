package com.jsc.hotspot.db.dao.ext;

import com.jsc.hotspot.db.domain.CameraTargetFace;
import com.jsc.hotspot.db.domain.CameraTargetFaceExample;
import com.jsc.hotspot.db.entity.TargetFaceResult;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface HotTargetFaceEXTMapper {
    int deleteById(@Param("targetIds") String [] targetIds);
    TargetFaceResult selectById(@Param("target_id") Long target_id);
    List<TargetFaceResult> selectByMyExampleSelective(@Param("example") CameraTargetFaceExample example, @Param("selective") CameraTargetFace.Column ... selective);
}
