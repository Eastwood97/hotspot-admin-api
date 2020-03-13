package com.jsc.hotspot.api.controller;

import com.jsc.hotspot.api.dto.TargetFace;
import com.jsc.hotspot.api.service.TargetFaceService;
import com.jsc.hotspot.common.utils.response.ResponseUtil;
import com.jsc.hotspot.db.domain.CameraTargetFace;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * @author tzm
 * @desc   布控目标人脸的接口数据
 */
@RestController
@RequestMapping("/admin/targetFace")
public class TargetFaceController {

    @Autowired
    private  TargetFaceService targetFaceService;

    /**
     * 添加目标人脸信息
     * @param targetFace
     * @return
     */
    @PostMapping
    public Object creat(@RequestBody TargetFace targetFace){
        targetFaceService.add(targetFace);
        return ResponseUtil.ok("添加成功");
    }

    /**
     * 编辑布控人脸的信息
     * @param targetFace
     * @return
     */
    @PutMapping
    public Object update(@RequestBody TargetFace targetFace){
        if(targetFaceService.update(targetFace)==0){
            return ResponseUtil.updatedDataFailed();
        }
        return ResponseUtil.ok(targetFace);
    }

    /**
     * 分页查询
     * @param page
     * @param limit
     * @param targetName
     * @return
     */
    @GetMapping
    public Object query(  @RequestParam(defaultValue = "1") Integer page,
                          @RequestParam(defaultValue = "20") Integer limit,
                             String targetName){
        List<CameraTargetFace> targetFaces=targetFaceService.getTargetFace(page,limit,targetName);
        return ResponseUtil.okList(targetFaces);
    }

    /**
     * 批量删除
     * @param targetIds
     * @return
     */
    @DeleteMapping
    public Object deleteById(@RequestBody String targetIds){
        if(targetFaceService.deleteById(targetIds)){
            return  ResponseUtil.ok();
        }else {
            return  ResponseUtil.deleteDataFailed();
        }
    }

}
