package com.jsc.hotspot.api.service;

import com.jsc.hotspot.db.domain.HotFrontDevice;

import java.util.List;

/**
 * 设备
 */
public interface DeviceService {
    /**
     * 添加设备
     * @param hotFrontDevice
     */
    void  add(HotFrontDevice hotFrontDevice);

    /**
     * 根据id删除设备消息
     */
    boolean deleteById(String devIds);

    /**
     * 根据id修改设备信息
     * @return
     */
    int updateById(HotFrontDevice hotFrontDevice);

    /**
     * 根据id查找设备信息
     * @param hotFrontDevice
     * @return
     */
    HotFrontDevice selectById(HotFrontDevice hotFrontDevice);
    /**
     * 获取设备集合
     * @return
     */
    List<HotFrontDevice> getDevices(Integer page, Integer limit, String devName, String devType, String devNum);

    HotFrontDevice getDeviceByDevName(String devName);

    List<HotFrontDevice> getDeviceList();

}
