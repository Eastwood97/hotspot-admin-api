package com.jsc.hotspot.api.controller;


import com.jsc.hotspot.api.facade.WeedFSService;
import com.jsc.hotspot.common.biz.BizResult;
import com.jsc.hotspot.common.utils.response.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.util.Base64Utils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.Duration;
import java.time.temporal.TemporalUnit;

/**
 * @author tzm
 * @desc 向文件系統中進行圖片的增加和刪除，將相應數據存入redis
 */
@RequestMapping("/admin/storage")
@RestController
public class AdminStorageController {

    @Autowired
    private WeedFSService weedFSService;

    @Autowired
    private RedisTemplate<String, String> redisTemplate;
    /**
     * 上傳圖片
     * @param file
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "/create",method = RequestMethod.POST)
    public Object uploadPic(@RequestParam("file")MultipartFile file) throws IOException {
       BizResult<String> bizResult=weedFSService.storagePic(file.getInputStream());
        if(bizResult.getFlag()){
            ValueOperations<String, String> redisValueOptions = redisTemplate.opsForValue();
            byte[] pciBytes = file.getBytes();
            String transferToBase64 = Base64Utils.encodeToString(pciBytes);
            // 设置过期时间
            redisValueOptions.set(bizResult.getData(), transferToBase64, Duration.ofMinutes(20));
            return ResponseUtil.ok(bizResult.getData());
        }else {
            return ResponseUtil.uploadFailed();
        }
    }

    /**
     * 刪除圖片
     * @param fileId
     * @return
     */
    @RequestMapping(value = "/delete",method = RequestMethod.DELETE)
    public Object deletePic(@RequestBody String fileId){
        BizResult<Boolean> bizResult=weedFSService.deletePic(fileId);
        if(bizResult.getFlag()){
            redisTemplate.delete(fileId);
            return ResponseUtil.ok("删除成功");
        }else{
            return ResponseUtil.ok("删除失败");
        }

    }



}
