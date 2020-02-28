package com.jsc.hotspot.api.controller;

import com.jsc.hotspot.api.service.FaceCampareResultService;
import com.jsc.hotspot.common.utils.response.ResponseUtil;
import com.jsc.hotspot.db.domain.CameraCompareResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author tzm
 * @desc 人脸识别的数据接口
 */
@RequestMapping("admin/faceCampareResult")
@RestController
public class FaceCampareConroller {

    @Autowired
    private FaceCampareResultService faceCompareResultService;

    /**
     * 分页查询
     * @param page
     * @param limit
     * @param targetName
     * @param startTime
     * @param endTime
     * @return
     */
    @GetMapping
    public Object query(Integer page, Integer limit,String targetName,String startTime,String endTime){
        List<CameraCompareResult> cameraCompareResults=faceCompareResultService.query(page,limit,targetName,startTime,endTime);
        return ResponseUtil.okList(cameraCompareResults);
    }

    /**
     * 批量删除
     * @param ids
     * @return
     */
    @DeleteMapping
    public Object delete(@RequestBody String ids){
        if(faceCompareResultService.deleteById(ids)){
            return  ResponseUtil.ok();
        }else{
            return  ResponseUtil.deleteDataFailed();
        }
    }
}
