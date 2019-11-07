package com.jsc.hotspot.api.facade.impl;

import com.jsc.hotspot.api.facade.WeedFSService;
import com.jsc.hotspot.common.biz.BizResult;
import com.jsc.hotspot.common.data.StorageConfig;
import com.jsc.hotspot.common.storage.weed.WeedStorage;
import com.jsc.hotspot.common.util.RandomNameUtil;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.InputStream;

/**
 * @author huixing
 * @description 文件服务接口实现
 * @date 2019/11/4
 */
public class WeedFSServiceImpl implements WeedFSService {

    /**
     * 文件系统工具类
     */
    @Autowired
    private WeedStorage weedStorage;

    public BizResult<String> storagePic(InputStream picInputStream) {
        int fileName = RandomNameUtil.getNum(0, 100000000);
        String url = weedStorage.store(picInputStream, "", fileName + "");

        // TODO 审核数据库之后进行入库操作

        return BizResult.create(url);
    }

    // TODO 优化配置

    public void init() throws Exception{
        StorageConfig storageConfig = new StorageConfig();
        storageConfig.setIpAddr("192.168.95.101");
        storageConfig.setPort(9333);
        weedStorage.config(storageConfig);
    }

    public BizResult<InputStream> getPic(String fileId){

        InputStream picInputStream = weedStorage.get(fileId);

        return BizResult.create(picInputStream);
    }

    public BizResult<Boolean> deletePic(String fileId)
    {
        boolean deleteFlag = weedStorage.delete(fileId);

        return BizResult.create(deleteFlag);
    }
}