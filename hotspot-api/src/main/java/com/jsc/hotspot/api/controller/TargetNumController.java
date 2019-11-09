package com.jsc.hotspot.api.controller;

import com.jsc.hotspot.api.service.TargetNumService;
import com.jsc.hotspot.common.utils.response.ResponseUtil;
import com.jsc.hotspot.db.domain.HotTargetInfo;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/admin/targetNum")
public class TargetNumController {
    private final Log logger= LogFactory.getLog(TargetNumController.class);

    @Autowired
    public TargetNumService targetNumService;

    @GetMapping
    public Object TargetNum(String targetName,String imsi,String imei,String isdn,
                            @RequestParam(defaultValue = "1") Integer page,
                            @RequestParam(defaultValue = "10") Integer limit
                          ){
        List<HotTargetInfo> targetNumList=targetNumService.query(page,limit,targetName,imsi,imei,isdn);
        return ResponseUtil.okList(targetNumList);
    }

}
