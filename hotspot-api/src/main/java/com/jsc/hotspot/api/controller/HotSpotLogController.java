package com.jsc.hotspot.api.controller;

import com.jsc.hotspot.api.service.HotSpotLogService;
import com.jsc.hotspot.api.service.LogService;
import com.jsc.hotspot.common.utils.response.ResponseUtil;
import com.jsc.hotspot.db.entity.PageResult;
import com.jsc.hotspot.db.entity.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Auther: WW
 * @Date: 2020/2/21 0021 14:16
 * @Description:
 */
@RestController
@RequestMapping("admin/hotspotLogResult")
public class HotSpotLogController {
    @Autowired
    private HotSpotLogService hotSpotLogService;
    /**
     *
     * 功能描述: 获取日志分页数据
     *
     * @param:
     * @return:
     * @auther: ww
     * @date: 2020/2/21 0021 14:18
     */
    @RequestMapping(value = "/hotspotLog",method = RequestMethod.GET)
    public Object getSysLog(String username, Integer currentPage, Integer pageSize){
        PageResult hotSpotLog = hotSpotLogService.selectSysLog(username, currentPage, pageSize);
        return ResponseUtil.ok(hotSpotLog);
    }
    @LogService(value="删除日志")
    @RequestMapping(value = "/hotspotLog", method = RequestMethod.DELETE)
    public Object deleteSysLog(@RequestBody String id) {
        try {
            hotSpotLogService.deleteSysLog(id);
            return ResponseUtil.ok(new Result(true,"删除成功!"));
        }catch (Exception e){
            return ResponseUtil.ok(new Result(false,"删除失败!"));
        }
    }
}
