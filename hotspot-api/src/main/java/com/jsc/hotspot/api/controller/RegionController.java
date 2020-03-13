package com.jsc.hotspot.api.controller;

import com.jsc.hotspot.api.service.RealtedNumService;
import com.jsc.hotspot.api.service.RegionService;
import com.jsc.hotspot.common.utils.response.ResponseUtil;
import com.jsc.hotspot.db.domain.HotTargetInfo;
import com.jsc.hotspot.db.domain.Region;
import com.jsc.hotspot.db.domain.RelatedNumResult;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author tzm
 * @desc 人脸识别与关联号码的数据接口
 */
@RequestMapping("/admin/region")
@RestController
public class RegionController {

    @Autowired
    private RegionService regionService;

    @RequestMapping("/getAll")
    public  Object getAll(){
        List<Region> regions=regionService.selectAll();
        return  ResponseUtil.okList(regions);
    }
    @GetMapping
    public Object query(@RequestParam(defaultValue = "1") Integer page,
                        @RequestParam(defaultValue = "10")Integer limit,
                        String regionName,Integer state){
        List<Region> regions=regionService.query(page,limit,regionName,state);
        return ResponseUtil.okList(regions);
    }

    /**
     * 批量删除
     * @param ids
     * @return
     */
    @DeleteMapping
    public Object delete(@RequestBody String ids) {
        if (regionService.deleteById(ids)) {
            return  ResponseUtil.ok();
        } else {
            return ResponseUtil.deleteDataFailed();
        }
    }
    /**
     * 添加
     * @param region
     * @return
     */
    @PostMapping
    public Object add(@RequestBody Region region){
        if(StringUtils.isEmpty(region.getRegionName()))
            return ResponseUtil.badArgument();
        regionService.add(region);
        return ResponseUtil.ok(region);
    }

    /**
     * 编辑
     * @param region
     * @return
     */
    @PutMapping
    public Object Update(@RequestBody Region region){
        if(StringUtils.isEmpty(region.getRegionName()))
            return ResponseUtil.badArgument();
        if(regionService.updateById(region)==0){
            return ResponseUtil.updatedDataFailed();
        }
        return ResponseUtil.ok(region);
    }

    @RequestMapping("/getRegionNameCount")
    @GetMapping
    public Object getRegionNameCount(String regionName){
        if(StringUtils.isEmpty(regionName)){
            return null;
        }
        long count=regionService.getRegionNameCount(regionName);
        return ResponseUtil.ok(count);
    }


}
