package com.jsc.hotspot.api.controller;

import com.jsc.hotspot.api.service.FaceCampareResultService;
import com.jsc.hotspot.common.utils.response.ResponseUtil;
import com.jsc.hotspot.db.domain.CameraCompareResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("admin/faceCampareResult")
@RestController
public class FaceCampareConroller {

    @Autowired
    private FaceCampareResultService faceCompareResultService;

    //查询
    @GetMapping
    public Object query(Integer page, Integer limit,String targetName,String startTime,String endTime){
        List<CameraCompareResult> cameraCompareResults=faceCompareResultService.query(page,limit,targetName,startTime,endTime);
        return ResponseUtil.okList(cameraCompareResults);
    }

    //删除
    @DeleteMapping
    public Object delete(@RequestBody String ids){
        if(faceCompareResultService.deleteById(ids)){
            return  ResponseUtil.ok();
        }else{
            return  ResponseUtil.deleteDataFailed();
        }
    }
}
