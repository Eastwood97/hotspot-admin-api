package com.jsc.hotspot.api.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * 布控人脸数据接口
 * @author tzm
 */
@RestController
@RequestMapping("/admin/targetFace")
public class TargetFaceController {

    @PostMapping
    public Object creat(MultipartFile file){

        return null;
    }

}
