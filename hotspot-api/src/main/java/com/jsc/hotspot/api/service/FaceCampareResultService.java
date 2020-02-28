package com.jsc.hotspot.api.service;

import com.jsc.hotspot.db.domain.CameraCompareResult;

import java.util.List;
/**
 * @author tzm
 * @desc 处理人脸识别结果的相关业务
 */
public interface FaceCampareResultService {
    /**
     * 分页查询
     * @param page
     * @param limit
     * @param targetName
     * @param startTime
     * @param endTime
     * @return
     */
    List<CameraCompareResult> query(Integer page, Integer limit,String targetName,String startTime,String endTime);

    /**
     * 批量删除
     * @param ids
     * @return
     */
    Boolean deleteById(String ids);
}
