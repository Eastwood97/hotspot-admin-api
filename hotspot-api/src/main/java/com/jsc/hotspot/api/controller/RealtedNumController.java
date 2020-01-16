package com.jsc.hotspot.api.controller;

import com.jsc.hotspot.api.service.RealtedNumService;
import com.jsc.hotspot.common.utils.response.ResponseUtil;
import com.jsc.hotspot.db.domain.HotFrontDevice;
import com.jsc.hotspot.db.domain.RelatedNumResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("admin/relatedNumResult")
@RestController
public class RealtedNumController {
    @Autowired
    private RealtedNumService realtedNumService;


    @GetMapping
    public Object query(@RequestParam(defaultValue = "1") Integer page,
                        @RequestParam(defaultValue = "10")Integer limit,
                        String targetName){
        List<RelatedNumResult> relatedNumResults=realtedNumService.query(page,limit,targetName);
        return ResponseUtil.okList(relatedNumResults);
    }

    @DeleteMapping
    public Object delete(@RequestBody String ids) {
        if (realtedNumService.deleteById(ids)) {
            return  ResponseUtil.ok();
        } else {
            return ResponseUtil.deleteDataFailed();
        }
    }
}
