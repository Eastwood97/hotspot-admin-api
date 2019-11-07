package com.jsc.hotspot.api.facade;


import com.jsc.hotspot.common.biz.BizResult;
import org.springframework.stereotype.Component;

import java.io.InputStream;

/**
 * @author huixing
 * @description weedFS操作服务处理
 * @date 2019/10/29
 */
@Component
public interface WeedFSService {

    /**
     * 圖片存儲方法
     *
     * @param picInputStream 圖片輸入流
     */
    BizResult<String> storagePic(InputStream picInputStream);

    // TODO 优化配置

    /**
     * 进行文件系统初始化操作
     */
    void init() throws Exception;

    /**
     * 获取图片
     * @param fileId 文件ID
     * @return
     */
    BizResult<InputStream> getPic(String fileId);

    /**
     * 删除图片
     * @param fileId 文件ID
     */
    BizResult<Boolean> deletePic(String fileId);
}
