package com.jsc.hotspot.api.controller;

import com.jsc.hotspot.api.dto.TargetFace;
import com.jsc.hotspot.api.service.LogService;
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
    @LogService(value="添加人脸")
    @PostMapping
    public Object creat(@RequestBody TargetFace targetFace){
        targetFaceService.add(targetFace);
        return ResponseUtil.ok("添加成功");
    }
    @LogService(value="修改人脸")
    @PutMapping
    public Object update(@RequestBody TargetFace targetFace){
        if(targetFaceService.update(targetFace)==0){
            return ResponseUtil.updatedDataFailed();
        }
        return ResponseUtil.ok(targetFace);
    }

    @GetMapping
    public Object query(  @RequestParam(defaultValue = "1") Integer page,
                          @RequestParam(defaultValue = "20") Integer limit,
                             String targetName){
        List<CameraTargetFace> targetFaces=targetFaceService.getTargetFace(page,limit,targetName);
        return ResponseUtil.ok(targetFaces);
    }
    @LogService(value="删除人脸")
    @DeleteMapping
    public Object deleteById(@RequestBody String targetIds){
        if(targetFaceService.deleteById(targetIds)){
            return  ResponseUtil.ok();
        }else {
            return  ResponseUtil.deleteDataFailed();
        }
    }

}
