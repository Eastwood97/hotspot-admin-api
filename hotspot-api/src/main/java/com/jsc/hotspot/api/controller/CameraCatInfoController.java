package com.jsc.hotspot.api.controller;

import com.jsc.hotspot.api.service.CameraCatInfoService;
import com.jsc.hotspot.api.service.FaceCampareResultService;
import com.jsc.hotspot.common.utils.response.ResponseUtil;
import com.jsc.hotspot.db.dao.CameraCatInfoMapper;
import com.jsc.hotspot.db.domain.CameraCatInfo;
import com.jsc.hotspot.db.domain.CameraCompareResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author tzm
 * 人脸捕获历史
 */
@RequestMapping("admin/cameraCatInfo")
@RestController
public class CameraCatInfoController {

    @Autowired
    private CameraCatInfoService cameraCatInfoService;

    //查询
    @GetMapping
    public Object query(Integer page, Integer limit,String startTime,String endTime){
        List<CameraCatInfo> cameraCatInfos=cameraCatInfoService.query(page,limit,startTime,endTime);
        return ResponseUtil.okList(cameraCatInfos);
    }

    //删除
    @DeleteMapping
    public Object delete(@RequestBody String ids){
        if(cameraCatInfoService.deleteById(ids)){
            return  ResponseUtil.ok();
        }else{
            return  ResponseUtil.deleteDataFailed();
        }

    }
}
