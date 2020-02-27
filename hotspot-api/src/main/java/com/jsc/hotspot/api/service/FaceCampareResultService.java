package com.jsc.hotspot.api.service;

import com.jsc.hotspot.db.domain.CameraCompareResult;

import java.util.List;

public interface FaceCampareResultService {
    List<CameraCompareResult> query(Integer page, Integer limit, String targetName, String startTime, String endTime);

    Boolean deleteById(String ids);
}
