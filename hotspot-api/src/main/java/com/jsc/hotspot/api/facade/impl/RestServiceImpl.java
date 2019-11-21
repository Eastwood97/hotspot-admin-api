package com.jsc.hotspot.api.facade.impl;

import com.alibaba.fastjson.JSON;
import com.jsc.hotspot.api.dto.FilesResultDTO;
import com.jsc.hotspot.api.dto.FolderResultDTO;
import com.jsc.hotspot.api.dto.RTSPStreamDTO;
import com.jsc.hotspot.api.facade.RestService;
import com.jsc.hotspot.common.biz.BizResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author huixing
 * @description 向流媒体发送请求接收数据
 * @date 2019/11/20
 */
@Service
public class RestServiceImpl implements RestService {


    @Autowired
    private RestTemplate restTemplate;


    public BizResult<FolderResultDTO> getFolder(){
        String notice = restTemplate.getForObject("http://192.201.102.100:10008/api/v1/record/folders"
                , String.class);

        FolderResultDTO folderResultDTO = JSON.parseObject(notice, FolderResultDTO.class);
        return BizResult.create(folderResultDTO);
    }

    public BizResult<FilesResultDTO> getFiles(){
        String notice = restTemplate.getForObject("http://192.201.102.100:10008/api/v1/record/files?folder=stream_265"
                , String.class);
        FilesResultDTO filesResultDTO = JSON.parseObject(notice, FilesResultDTO.class);
        return BizResult.create(filesResultDTO);
    }

    public BizResult<String> addRTSPStream(RTSPStreamDTO rtspStreamDTO){
        // http://192.201.102.100:10008/api/v1/stream/start?url=rtsp://admin:admin12345@192.201.102.201&customPath=stream_265&transType=TCP&idleTimeout=10&heartbeatInterval=60
        String notice = restTemplate.getForObject("http://192.201.102.100:10008/api/v1/stream/start?url="
                        + rtspStreamDTO.getUrl()
                        + "&customPath=" + rtspStreamDTO.getCustomPath()
                + "&transType=" + rtspStreamDTO.getTransType()
                + "&idleTimeout=" + rtspStreamDTO.getIdleTimeout()
                + "&heartbeatInterval=" + rtspStreamDTO.getHeartbeatInterval()
                , String.class);
        return BizResult.create(notice);
    }

    public void deleteRTSPStream(String rtspId){
        // http://192.201.102.100:10008/api/v1/stream/start?url=rtsp://admin:admin12345@192.201.102.201&customPath=stream_265&transType=TCP&idleTimeout=10&heartbeatInterval=60
        String notice = restTemplate.getForObject("http://192.201.102.100:10008//api/v1/stream/stop?id=" + rtspId

                , String.class);
    }

}
