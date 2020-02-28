package com.jsc.hotspot.api.service;

import com.jsc.hotspot.api.dto.TargetFace;
import com.jsc.hotspot.db.domain.CameraTargetFace;

import java.lang.annotation.Target;
import java.util.List;
/**
 * @author tzm
 * @desc 处理目标人脸的相关业务
 */
public interface TargetFaceService {
    /**
     * 添加目标
     * @param targetFace
     */
    void add(TargetFace targetFace);
    /**
     * 批量删除
     * @param targetIds
     * @return
     */
    boolean deleteById(String targetIds);

    /**
     * 修改目标人脸的数据
     * @param targetFace
     * @return
     */
    int update(TargetFace targetFace);

    /**
     * 分页查询
     * @param page
     * @param limit
     * @param targetName
     * @return
     */
    List<CameraTargetFace> getTargetFace(Integer page,Integer limit,String targetName);

}
