package com.jsc.hotspot.api.service;

import com.jsc.hotspot.db.domain.CaptureCar;

import java.util.List;

/**
 * @author tzm
 * 处理抓拍车牌数据的相关业务
 */
public interface CaptureCarService {
    /**
     * 分页查询抓拍车牌
     * @param page
     * @param limit
     * @param startTime
     * @param endTime
     * @return
     */
    List<CaptureCar> query(Integer page, Integer limit,String startTime,String endTime,String plateNumber);

    /**
     * 批量删除
     * @param ids
     * @return
     */
    boolean deleteById(String ids);
}
