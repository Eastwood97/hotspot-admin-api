package com.jsc.hotspot.api.service;


import com.jsc.hotspot.db.domain.HotTargetInfo;

import java.util.List;

public interface TargetNumService {

    List<HotTargetInfo> query(Integer page, Integer limit,String targetName,String imsi,String imei,String isdn);

    int updateById(HotTargetInfo targetInfo);

    void deleteById(Long[] ids);

    void add(HotTargetInfo targetInfo);



}
