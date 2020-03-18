package com.jsc.hotspot.api.controller;

import com.jsc.hotspot.api.service.DeviceService;
import com.jsc.hotspot.api.service.HoTnumInfoService;
import com.jsc.hotspot.api.service.HotFrontDeviceOptionService;
import com.jsc.hotspot.common.utils.response.ResponseUtil;
import com.jsc.hotspot.db.domain.HotFrontDevice;
import com.jsc.hotspot.db.domain.HotFrontDeviceOption;
import com.jsc.hotspot.db.entity.PageResult;
import com.jsc.hotspot.db.entity.Traffic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * @Auther: WW
 * @Date: 2019/12/23 0023 10:59
 * @Description:
 */
@RestController
@RequestMapping("/admin/fenxi")
public class DataFenXiController {
    @Autowired
    private HoTnumInfoService hoTnumInfoService;
    @Autowired
    private DeviceService deviceService;
    @Autowired
    private HotFrontDeviceOptionService hotFrontDeviceOptionService;

    /**
     * 功能描述: 根据人流量统计数据
     *
     * @param:
     * @return:
     * @auther: ww
     * @date: 2019/11/29 0029 15:28
     */
    @RequestMapping(value = "/traffic", method = RequestMethod.POST)
    public Object getTraffic(@RequestBody Traffic traffic) {
        try {
            List<Map> trafficList = hoTnumInfoService.getTraffic(traffic.getDevId(), traffic.getCreateTime());
            return ResponseUtil.ok(trafficList);
        } catch (Exception e) {
            return ResponseUtil.fail();
        }
    }

    /**
     * 功能描述: 根据国别统计数据
     *
     * @param:
     * @return:
     * @auther: ww
     * @date: 2019/11/29 0029 15:28
     */
    @RequestMapping(value = "/CountryCount", method = RequestMethod.POST)
    public Object getCountryCount(@RequestBody Traffic traffic) {
        try {
            Object differentCountries = hoTnumInfoService.getDifferentCountries(traffic.getDevId(), traffic.getCreateTime());
            return ResponseUtil.ok(differentCountries);
        } catch (Exception e) {
            return ResponseUtil.fail();
        }
    }

    /**
     * 功能描述: 获取区域数组
     *
     * @param:
     * @return: Option
     * @auther: ww
     * @date: 2019/11/29 0029 15:07
     */
    @RequestMapping(value = "/device", method = RequestMethod.GET)
    public Object getDeviceList() {
        try {
            List<HotFrontDevice> deviceList = deviceService.getDeviceList();
            return ResponseUtil.ok(deviceList);
        } catch (Exception e) {
            return ResponseUtil.fail();
        }
    }

    /**
     * 功能描述: 获取设备数组
     *
     * @param:
     * @return: OptionMessage
     * @auther: ww
     * @date: 2019/11/29 0029 15:07
     */
    @RequestMapping(value = "/devicemessage", method = RequestMethod.GET)
    public Object getOptionMessageList(Integer id) {
        try {
            List<HotFrontDeviceOption> hotFrontDeviceOption = hotFrontDeviceOptionService.getHotFrontDeviceOption(id);
            return ResponseUtil.ok(hotFrontDeviceOption);
        } catch (Exception e) {
            return ResponseUtil.fail();
        }
    }
    /**
     *
     * 功能描述: 同行分析
     *
     * @param:
     * @return:
     * @auther: ww
     * @date: 2019/12/16 0016 14:54
     */
//    @RequestMapping(value = "/tongxingList", method = RequestMethod.GET)
//    public Object getTongXingLists(Integer currentPage, Integer pageSize, Integer createTime,String imsi) {
//        try {
//            PageResult pageResult = hoTnumInfoService.selecttongxingList(currentPage, pageSize,createTime,imsi);
//            return ResponseUtil.ok(pageResult);
//        }catch (Exception e){
//            return ResponseUtil.fail();
//        }
////
//    }

    /**
     * 功能描述: 归属地点击分析
     *
     * @param:
     * @return:
     * @auther: ww
     * @date: 2020/3/16 0016 15:13
     */
    @RequestMapping(value = "/getGuiShuiDiList", method = RequestMethod.GET)
    public Object getGuiShuiDiList(String imsi, int page, int row,String guishu) {
        try {
            PageResult guiShuiDiList = hoTnumInfoService.getGuiShuiDiList(imsi, page, row, guishu);
            return ResponseUtil.ok(guiShuiDiList);
        } catch (Exception e) {
            return ResponseUtil.fail();
        }
    }
}
