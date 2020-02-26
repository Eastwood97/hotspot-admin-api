package com.jsc.hotspot.api.controller;

import com.jsc.hotspot.api.service.HoTnumInfoService;
import com.jsc.hotspot.common.utils.response.ResponseUtil;
import com.jsc.hotspot.db.domain.HotNumInfo;
import com.jsc.hotspot.db.entity.PageResult;
import com.jsc.hotspot.db.entity.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Auther: WW
 * @Date: 2019/11/7 0007 08:24
 * @Description:取号库
 */
@RestController
@RequestMapping("/admin/hot-numinfo")
public class HoTnumInfoController {
    @Autowired
    private HoTnumInfoService hoTnumInfoService;
    /**
     *
     * 功能描述:获取取号信息
     *
     * @param: page , row
     * @return: PageResult
     * @auther: ww
     * @date: 2019/11/7 0007 9:00
     */
    @RequestMapping(value = "/hotnuminfo",method = RequestMethod.GET)
    public Object findHoTnumInfo(int groupId, int page, int row, HotNumInfo hotNumInfoDAO){
        PageResult hotNumInfo = hoTnumInfoService.findHotNumInfo(groupId,page, row, hotNumInfoDAO);
        return ResponseUtil.ok(hotNumInfo);
    }

    /**
     *
     * 功能描述: 删除取号信息
     *
     * @param: ids
     * @return: Result
     * @auther: ww
     * @date: 2019/11/7 0007 9:00
     */
    @RequestMapping(value = "hotnuminfo", method = RequestMethod.DELETE)
    public Object deleteHoTnumInfo(@RequestBody String ids){
        try {
            hoTnumInfoService.deleteHotNumInfo(ids);
            return ResponseUtil.ok(new Result(true,"删除成功!"));
        }catch (Exception e){
            return ResponseUtil.ok(new Result(false,"删除失败!"));
        }
    }
}
