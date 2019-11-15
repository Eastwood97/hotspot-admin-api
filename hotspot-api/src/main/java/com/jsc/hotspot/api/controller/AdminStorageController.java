package com.jsc.hotspot.api.controller;


import com.jsc.hotspot.api.facade.WeedFSService;
import com.jsc.hotspot.common.biz.BizResult;
import com.jsc.hotspot.common.utils.response.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RequestMapping("/admin/storage")
@RestController
public class AdminStorageController {
    @Autowired
    private WeedFSService weedFSService;



    @RequestMapping(value = "/create",method = RequestMethod.POST)
    public Object uploadPic(@RequestParam("file")MultipartFile file) throws IOException {
       BizResult<String> bizResult=weedFSService.storagePic(file.getInputStream());
        if(bizResult.getFlag()){
            return ResponseUtil.ok(bizResult.getData());
        }else {
            return ResponseUtil.uploadFailed();
        }

    }
    @RequestMapping(value = "/delete",method = RequestMethod.DELETE)
    public Object deletePic(@RequestBody String fileId){
        BizResult<Boolean> bizResult=weedFSService.deletePic(fileId);
        if(bizResult.getFlag()){
            return ResponseUtil.ok("删除成功");
        }else{
            return ResponseUtil.ok("删除失败");
        }

    }



}
