package com.jsc.hotspot.api.controller;


import com.jsc.hotspot.api.facade.WeedFSService;
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
        System.out.println(file.getInputStream());
//       BizResult<String> bizResult=weedFSService.storagePic(file.getInputStream());
//        if(bizResult.getFlag()){
//            return ResponseUtil.ok(bizResult.getData());
//        }else {
//            return ResponseUtil.uploadFailed();
//        }
        return ResponseUtil.ok(123456789);

    }
    @RequestMapping(value = "/delete",method = RequestMethod.DELETE)
    public Object deletePic(@RequestBody String fileId){

        return ResponseUtil.ok("删除成功");
    }



}
