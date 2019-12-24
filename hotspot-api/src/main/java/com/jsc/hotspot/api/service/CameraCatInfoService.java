package com.jsc.hotspot.api.service;

import com.jsc.hotspot.db.domain.CameraCatInfo;

import java.util.List;

public interface CameraCatInfoService {

    List<CameraCatInfo> query(Integer page, Integer limit,String startTime,String endTime);

    boolean deleteById(String ids);

}
