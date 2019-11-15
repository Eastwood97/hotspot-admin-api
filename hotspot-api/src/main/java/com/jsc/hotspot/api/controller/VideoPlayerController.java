package com.jsc.hotspot.api.controller;

import com.jsc.hotspot.api.service.VideoPlayerService;
import com.jsc.hotspot.common.biz.BizResult;
import com.jsc.hotspot.common.utils.response.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author huixing
 * @description 视频播放控制层
 * @date 2019/11/10
 */
@RestController
@RequestMapping("/admin/video")
public class VideoPlayerController {

    @Autowired
    private VideoPlayerService videoPlayerService;

    /**
     *
     * @return
     */
    @GetMapping("/live")
    public Object getVideoRTSPUrl(){
        BizResult<List<String>> listBizResult = videoPlayerService.getVideoRTSPUrl();
        if (listBizResult.getFlag()){
            return ResponseUtil.fail(400, listBizResult.getDesc());
        }else {
            return ResponseUtil.okList(listBizResult.getData());
        }
    }
}
