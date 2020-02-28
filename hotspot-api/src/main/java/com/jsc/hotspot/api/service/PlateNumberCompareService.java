package com.jsc.hotspot.api.service;


import com.jsc.hotspot.db.domain.PlateNumberCompare;

import java.util.List;

/**
 * @author tzm
 * @desc 处理车牌比对与关联的相关业务
 */
public interface PlateNumberCompareService {
    /**
     * 分页查询数据
     * @param page
     * @param limit
     * @param startTime
     * @param endTime
     * @param plateNumber
     * @return
     */
    List<PlateNumberCompare>  query(Integer page, Integer limit,String startTime,String endTime,String plateNumber);

    /**
     * 批量删除
     * @param ids
     * @return
     */
    boolean deleteById(String ids);
}
