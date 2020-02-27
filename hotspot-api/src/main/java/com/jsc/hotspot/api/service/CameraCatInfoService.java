package com.jsc.hotspot.api.service;

import com.jsc.hotspot.db.domain.CameraCatInfo;

import java.util.List;
import java.util.Map;

public interface CameraCatInfoService {

    List<CameraCatInfo> query(Integer page, Integer limit, String startTime, String endTime);

    boolean deleteById(String ids);
    Long getCameraCatInfoList();
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
