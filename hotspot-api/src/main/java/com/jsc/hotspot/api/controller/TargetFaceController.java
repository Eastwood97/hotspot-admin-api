package com.jsc.hotspot.api.controller;

import com.jsc.hotspot.api.dto.TargetFace;
import com.jsc.hotspot.api.service.TargetFaceService;
import com.jsc.hotspot.common.utils.response.ResponseUtil;
import com.jsc.hotspot.db.domain.CameraTargetFace;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * 布控人脸数据接口
 * @author tzm
 */
@RestController
@RequestMapping("/admin/targetFace")
public class TargetFaceController {

    @Autowired
    private  TargetFaceService targetFaceService;

    @PostMapping
    public Object creat(@RequestBody TargetFace targetFace){
        targetFaceService.add(targetFace);
        return ResponseUtil.ok("添加成功");
    }

    @PutMapping
    public Object update(@RequestBody TargetFace targetFace){
        if(targetFaceService.update(targetFace)==0){
            return ResponseUtil.updatedDataFailed();
        }
        return ResponseUtil.ok(targetFace);
    }

    public Object query(  @RequestParam(defaultValue = "1") Integer page,
                          @RequestParam(defaultValue = "20") Integer limit,
                             String targetName){
        List<CameraTargetFace> targetFaces=targetFaceService.getTargetFace();
        return ResponseUtil.ok(targetFaces);
    }

}
