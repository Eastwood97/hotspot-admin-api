package com.jsc.hotspot.api.controller;

import com.jsc.hotspot.api.service.CameraCatInfoService;
import com.jsc.hotspot.api.service.HoTnumInfoService;
import com.jsc.hotspot.api.service.HotCompareResultService;
import com.jsc.hotspot.api.service.HotTargetInfoService;
import com.jsc.hotspot.common.utils.response.ResponseUtil;
import com.jsc.hotspot.db.domain.HotCompareResult;
import com.jsc.hotspot.db.domain.HotNumInfo;
import com.jsc.hotspot.db.entity.CountList;
import com.jsc.hotspot.db.entity.HotCompareResultList;
import com.jsc.hotspot.db.entity.PageResult;
import com.jsc.hotspot.db.entity.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Auther: WW
 * @Date: 2019/11/7 0007 11:15
 * @Description:
 */
@RestController
@RequestMapping("/admin/hotcompare-result")
public class HotCompareResultController {
    @Autowired
    private HotCompareResultService hotCompareResultService;
    @Autowired
    private CameraCatInfoService cameraCatInfoService;
    @Autowired
    private HotTargetInfoService hotTargetInfoService;
    @Autowired
    private HoTnumInfoService hoTnumInfoService;

    /**
     * 功能描述: 获取中标信息
     *
     * @param: page, row, hotCompareResultDAO
     * @return: pageResult
     * @auther: ww
     * @date: 2019/11/7 0007 11:18
     */
    @RequestMapping(value = "/hotcompareresult", method = RequestMethod.GET)
    public Object findHotCompareResult(int page, int row, HotCompareResultList hotCompareResultList) {
        PageResult hotCompareResult = hotCompareResultService.findHotCompareResult(page, row, hotCompareResultList);
        return ResponseUtil.ok(hotCompareResult);
    }

    /**
     * 功能描述: 删除中标信息
     *
     * @param: Long []ids
     * @return: Result
     * @auther: ww
     * @date: 2019/11/7 0007 11:19
     */
    @RequestMapping(value = "hotcompareresult", method = RequestMethod.DELETE)
    public Object deleteHotCompareResult(@RequestBody String ids) {
        try {
            hotCompareResultService.deleteHotCompareResult(ids);
            return ResponseUtil.ok(new Result(true, "删除成功!"));
        } catch (Exception e) {
            return ResponseUtil.ok(new Result(false, "删除失败!"));
        }
    }

    /**
     * 功能描述: 获取黑名单总数及取号数量号码归属地分析，总布控数量把他们封装在一起
     *
     * @param: 无
     * @return: count
     * @auther: ww
     * @date: 2019/11/7 0007 13:50
     */
    @RequestMapping(value = "/countlist", method = RequestMethod.GET)
    public Object findHotCompareResultCount() {
        //人脸折线图
        List cameraCatInfoServiceHoTnumInfoDateNum = cameraCatInfoService.getHoTnumInfoDateNum();
        //获取人脸总量
        Long cameraCatInfoList = cameraCatInfoService.getCameraCatInfoList();
        //获取布控总量
        Long targetInfoNum = hotTargetInfoService.getHotTargetInfoNum();
        //取号总量
        Long hoTnumInfoNum = hoTnumInfoService.getHoTnumInfoNum();
        //获取15天的取号数量及其对应15天
        List<HotNumInfo> hoTnumInfoDateNum = hoTnumInfoService.getHoTnumInfoDateNum();
        //归属地分析
        List guiShuDiList = hoTnumInfoService.getGuiShuDiList();
        //今日取号数量
        Long todayHoTnumInfoNum = hoTnumInfoService.getTodayHoTnumInfoNum();
        return ResponseUtil.ok(new CountList(hoTnumInfoDateNum, targetInfoNum, guiShuDiList, cameraCatInfoList, hoTnumInfoNum,todayHoTnumInfoNum,cameraCatInfoServiceHoTnumInfoDateNum));
    }

}