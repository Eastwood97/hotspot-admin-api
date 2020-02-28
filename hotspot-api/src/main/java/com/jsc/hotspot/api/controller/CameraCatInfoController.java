package com.jsc.hotspot.api.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.jsc.hotspot.api.service.CameraCatInfoService;
import com.jsc.hotspot.api.service.FaceCampareResultService;
import com.jsc.hotspot.api.service.TargetFaceService;
import com.jsc.hotspot.api.utils.HttpUtil;
import com.jsc.hotspot.common.utils.response.ResponseUtil;
import com.jsc.hotspot.db.dao.CameraCatInfoMapper;
import com.jsc.hotspot.db.domain.CameraCatInfo;
import com.jsc.hotspot.db.domain.CameraCompareResult;
import com.sun.jdi.Method;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

/**
 * @author tzm
 * @desc 人脸捕获历史的数据接口
 */
@RequestMapping("admin/cameraCatInfo")
@RestController
public class CameraCatInfoController {

    @Autowired
    private CameraCatInfoService cameraCatInfoService;

    private TargetFaceService targetFaceService;

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
     * 批量删除
     * @param rowList
     * @return
     */
    @DeleteMapping
    public Object delete(@RequestBody String rowList){
        // 解析接收的JSON字符串
        List<CameraCatInfo> listObject = (List<CameraCatInfo>) JSONArray.parse(rowList);
        for (int i=0;i<listObject.size();i++){
            CameraCatInfo cameraCatInfo=listObject.get(i);
            if(cameraCatInfo.getQuality()==1){//该抓拍是中标提示
//                targetFaceService.deleteByTime(cameraCatInfo.getCaptureTime());
            }

        }
//        以数组作为参数，遍历删除抓拍
//        cameraCatInfoService.deleteByList(listObject);




        return  ResponseUtil.deleteDataFailed();
    }

    /**
     * 下载视频，此处还应添加参数 :设备ip
     * @param captureTime
     * @return
     */
    @RequestMapping(value = "/downloadVedio",method = RequestMethod.GET)
    public Object download(@RequestBody String captureTime){
       //1.设置开始和结束时间  通道号作为参数发送http请求下载视频
        DateTimeFormatter df=DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime midTime= LocalDateTime.parse(captureTime,df);
        LocalDateTime startTime=midTime.minusSeconds(5);
        LocalDateTime endTime=midTime.plusSeconds(5);
        //2.返回值
        //  String reponse= HttpUtil.sendGet();
        //3.判断下载是否成功
        //3.1获取isDownload状态
        //3.2封装前端需要的数据： \1.isDownload状态 2.文件系统中的视频id
        return ResponseUtil.ok();

    }


}
