package com.jsc.hotspot.api.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.jsc.hotspot.api.facade.WeedFSService;
import com.jsc.hotspot.api.facade.impl.KafkaReceiverServiceImpl;
import com.jsc.hotspot.api.service.CameraCatInfoService;
import com.jsc.hotspot.api.service.FaceCampareResultService;
import com.jsc.hotspot.api.service.TargetFaceService;
import com.jsc.hotspot.api.utils.HttpUtil;
import com.jsc.hotspot.api.vo.DeleteParams;
import com.jsc.hotspot.common.biz.BizResult;
import com.jsc.hotspot.common.utils.response.ResponseUtil;
import com.jsc.hotspot.db.dao.CameraCatInfoMapper;
import com.jsc.hotspot.db.domain.CameraCatInfo;
import com.jsc.hotspot.db.domain.CameraCompareResult;
import com.sun.jdi.Method;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

/**
 * @author tzm
 * @desc 人脸捕获历史的数据接口
 */
@RequestMapping("admin/cameraCatInfo")
@RestController
public class CameraCatInfoController {

    private Log log= LogFactory.getLog(CameraCatInfoController.class);

    @Autowired
    private CameraCatInfoService cameraCatInfoService;

    @Autowired
    private WeedFSService weedFSService;

    /**
     * 多条件分页查询
     * @param page
     * @param limit
     * @param startTime
     * @param endTime
     * @return
     */
    @GetMapping
    public Object query(Integer page, Integer limit,String startTime,String endTime){
        List<CameraCatInfo> cameraCatInfos=cameraCatInfoService.query(page,limit,startTime,endTime);
        return ResponseUtil.okList(cameraCatInfos);
    }

    /**
     *
      * @param params
     * @return
     */
    @DeleteMapping
    public Object delete(@RequestBody DeleteParams params){
        BizResult<Boolean> bizResult=weedFSService.deletePic(params.getFileIds());
        if(!bizResult.getFlag()){
            return ResponseUtil.ok("删除失败");
        }
        if (cameraCatInfoService.deleteById(params.getIds())) {
            return  ResponseUtil.ok();
        } else {
            return ResponseUtil.deleteDataFailed();
        }
    }
}
