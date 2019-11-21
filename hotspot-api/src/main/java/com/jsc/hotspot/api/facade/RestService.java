package com.jsc.hotspot.api.facade;

import com.jsc.hotspot.api.dto.FilesResultDTO;
import com.jsc.hotspot.api.dto.FolderResultDTO;
import com.jsc.hotspot.api.dto.RTSPStreamDTO;
import com.jsc.hotspot.common.biz.BizResult;

/**
 * @author huixing
 * @description
 * @date 2019/11/20
 */
public interface RestService {

    /**
     * 获取文件夹
     * @return
     */
    BizResult<FolderResultDTO> getFolder();

    /**
     * 获取文件
     * @return
     */
    BizResult<FilesResultDTO> getFiles();

    /**
     * 增加RTSP流
     * @param rtspStreamDTO
     * @return
     */
    BizResult<String> addRTSPStream(RTSPStreamDTO rtspStreamDTO);
}
