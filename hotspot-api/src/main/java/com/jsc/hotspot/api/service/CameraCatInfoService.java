package com.jsc.hotspot.api.service;

import com.jsc.hotspot.db.domain.CameraCatInfo;

import java.util.List;
import java.util.Map;

/**
 * @author tzm
 * @desc 处理摄像头抓拍人脸的相关业务
 */
public interface CameraCatInfoService {
    /**
     * 分页查询
     * @param page
     * @param limit
     * @param startTime
     * @param endTime
     * @return
     */
    List<CameraCatInfo> query(Integer page, Integer limit,String startTime,String endTime);

    /**
     * 批量删除
     * @param ids
     * @return
     */
    boolean deleteById(String ids);

    /**
     * 查出抓拍数量
     * @return
     */
    Long getCameraCatInfoList();

    /**
     * 魏伟写的方法
     * @return
     */
    List getHoTnumInfoDateNum();

    Map<String, Integer> getFaceByDay();
    Map<String, Integer> getFaceByMonth();

    /**
     * 摄像机抓拍总数
     */
    Long countCameraCatNums();

    /**
     * 当天抓拍到的人脸数量
     * @return
     */
    Long currentCameraNums();
}
