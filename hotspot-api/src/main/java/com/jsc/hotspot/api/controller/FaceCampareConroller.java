package com.jsc.hotspot.api.controller;

import com.jsc.hotspot.api.facade.WeedFSService;
import com.jsc.hotspot.api.service.FaceCampareResultService;
import com.jsc.hotspot.common.biz.BizResult;
import com.jsc.hotspot.common.utils.response.ResponseUtil;
import com.jsc.hotspot.db.domain.CameraCompareResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * @author tzm
 * @desc 人脸识别的数据接口
 */
@RequestMapping("admin/faceCampareResult")
@RestController
public class FaceCampareConroller {

    @Autowired
    private WeedFSService weedFSService;

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
    public Object delete(@RequestBody String ids,String fileIds){
        BizResult<Boolean> bizResult=weedFSService.deletePic(fileIds);
        if(!bizResult.getFlag()){
            return ResponseUtil.ok("删除失败");
        }
        if(faceCompareResultService.deleteById(ids)){
            return  ResponseUtil.ok();
        }else{
            return  ResponseUtil.deleteDataFailed();
        }
    }

    /**
     * 下载视频，此处还应添加参数 :设备ip
     * @param captureTime
     * @return
     */
    @RequestMapping(value = "/downloadVedio",method = RequestMethod.GET)
    public Object download(String captureTime,String devIp){
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
