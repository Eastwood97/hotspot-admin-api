package com.jsc.hotspot.api.service.impl;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import com.jsc.hotspot.api.config.UrlConst;
import com.jsc.hotspot.api.dto.RecordVideoDTO;
import com.jsc.hotspot.api.service.VideoPlayerService;
import com.jsc.hotspot.api.utils.HttpUtil;
import com.jsc.hotspot.common.bean.VideoDownLoadBean;
import com.jsc.hotspot.common.biz.BizResult;
import com.jsc.hotspot.common.enums.DeviceTypeEnum;
import com.jsc.hotspot.db.dao.HotFrontDeviceMapper;
import com.jsc.hotspot.db.domain.CameraCompareResult;
import com.jsc.hotspot.db.domain.HotFrontDevice;
import com.jsc.hotspot.db.domain.HotFrontDeviceExample;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

/**
 * @author huixing
 * @description
 * @date 2019/11/10
 */
@Service
public class VideoPlayerServiceImpl implements VideoPlayerService{

    @Autowired
    private HotFrontDeviceMapper hotFrontDeviceMapper;

    private final Log logger = LogFactory.getLog(VideoPlayerServiceImpl.class);

    @Override
    public BizResult<List<String>> getVideoRTSPUrl() {
        HotFrontDeviceExample hotFrontDeviceExample = new HotFrontDeviceExample();
        HotFrontDeviceExample.Criteria criteria = hotFrontDeviceExample.createCriteria();
        criteria.andDevTypeEqualTo(DeviceTypeEnum.CAMERA.getCode());
        criteria.andDevTypeEqualTo(DeviceTypeEnum.ZNCAMERA.getCode());
        List<HotFrontDevice> hotFrontDevices = hotFrontDeviceMapper.selectByExampleSelective(hotFrontDeviceExample, HotFrontDevice.Column.ipAddr);
        List<String> list = new ArrayList<>();
        hotFrontDevices.forEach((hotFrontDevice -> {
            list.add(hotFrontDevice.getIpAddr());
        }));
        return BizResult.create(list);
    }

    @Override
    public BizResult<List<String>> getDownLoadVideo(RecordVideoDTO recordVideoDTO) {
        LocalDateTime captureTime = recordVideoDTO.getCaptureTime();
        VideoDownLoadBean videoDownLoadBean = new VideoDownLoadBean();
        videoDownLoadBean.setStartTime(captureTime.minusSeconds(5));
        videoDownLoadBean.setStopTime(captureTime.plusSeconds(5));
        String response = HttpUtil.sendPost(JSON.toJSONString(videoDownLoadBean), UrlConst.SDK_DOWNLOAD_URL);
        if (response != null){
            if (logger.isDebugEnabled()) {
                logger.debug("TargetFaceServiceImpl 通过Http请求进行布控：" + response);
            }
        }
        return null;
    }
}
