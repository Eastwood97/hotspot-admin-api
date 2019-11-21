package com.jsc.hotspot.api.service;

import com.jsc.hotspot.api.dto.TargetFace;
import com.jsc.hotspot.db.domain.CameraTargetFace;

import java.lang.annotation.Target;
import java.util.List;

public interface TargetFaceService {
    /**
     * 添加目标
     * @param targetFace
     */
    void add(TargetFace targetFace);

    boolean deleteById(String targetIds);

    int update(TargetFace targetFace);

    List<CameraTargetFace> getTargetFace(Integer page,Integer limit,String targetName);

}
