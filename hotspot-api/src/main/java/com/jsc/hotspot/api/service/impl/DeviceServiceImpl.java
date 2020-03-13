package com.jsc.hotspot.api.service.impl;

import com.github.pagehelper.PageHelper;
import com.jsc.hotspot.api.service.DeviceService;
import com.jsc.hotspot.db.dao.HotFrontDeviceMapper;
import com.jsc.hotspot.db.dao.ext.HotFrontDeviceEXTMapper;
import com.jsc.hotspot.db.domain.Admin;
import com.jsc.hotspot.db.domain.HotFrontDevice;
import com.jsc.hotspot.db.domain.HotFrontDeviceExample;
import com.jsc.hotspot.db.domain.HotTargetInfoExample;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.cert.Certificate;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @author tzm
 * @desc 处理设备数据相关业务
 */
@Service("DeviceService")
public class DeviceServiceImpl implements DeviceService {

    @Autowired
    private HotFrontDeviceMapper hotFrontDeviceMapper;

    @Autowired
    private HotFrontDeviceEXTMapper hotFrontDeviceEXTMapper;

    /**
     * 添加设备
     * @param hotFrontDevice
     */
    public void  add(HotFrontDevice hotFrontDevice){
        hotFrontDevice.setCreateTime(LocalDateTime.now());
        hotFrontDevice.setUpdateTime(LocalDateTime.now());
        hotFrontDeviceMapper.insertSelective(hotFrontDevice);
    }

    /**
     * 根据id删除设备消息
     */
    @Override
    public boolean deleteById(String devIds) {
        String[] split=devIds.split(",");
        int result=hotFrontDeviceEXTMapper.deleteById(split);
        return split.length==result;
    }

    /**
     * 根据id修改设备信息
     * @return
     */
    @Override
    public int updateById(HotFrontDevice hotFrontDevice) {
        hotFrontDevice.setUpdateTime(LocalDateTime.now());
        return  hotFrontDeviceMapper.updateByPrimaryKeySelective(hotFrontDevice);
    }

    /**
     * 根据id查找设备信息
     * @param hotFrontDevice
     * @return
     */
    @Override
    public HotFrontDevice selectById(HotFrontDevice hotFrontDevice) {
        return hotFrontDeviceMapper.selectByPrimaryKeySelective(hotFrontDevice.getDevId());
    }

    /**
     * 获取设备集合
     * @return
     */
    @Override
    public List<HotFrontDevice> getDevices(Integer page, Integer limit,String devName,String devType,String devNum) {
        HotFrontDeviceExample example=new HotFrontDeviceExample();
        HotFrontDeviceExample.Criteria certificate=example.createCriteria();
        if(!StringUtils.isEmpty(devName)){
            certificate.andDevNameLike("%"+devName+"%");
        }
        if(!StringUtils.isEmpty(devType)){
            certificate.andDevTypeEqualTo(Byte.valueOf(devType));
        }
        if(!StringUtils.isEmpty(devNum)){
            certificate.andDevTypeEqualTo(Byte.valueOf(devNum));
        }
        example.setOrderByClause("create_time desc");
        PageHelper.startPage(page, limit);
        return hotFrontDeviceMapper.selectByExampleSelective(example);
    }

    /**
     * 根据设备名获取设备
     * @param devName
     * @return
     */
    @Override
    public HotFrontDevice getDeviceByDevName(String devName) {
        HotFrontDeviceExample hotFrontDeviceExample = new HotFrontDeviceExample();
        HotFrontDeviceExample.Criteria criteria = hotFrontDeviceExample.createCriteria();
        criteria.andDevNameEqualTo(devName);
        HotFrontDevice hotFrontDevice = hotFrontDeviceMapper.selectOneByExampleSelective(hotFrontDeviceExample);
        return hotFrontDevice;
    }

    /**
     * 魏伟写的方法
     * @return
     */
    @Override
    public List<HotFrontDevice> getDeviceList() {
        return hotFrontDeviceEXTMapper.selectDeviceList();
    }

    /**
     * 功能描述: 根据regionId取内容
     *
     * @param regionId
     * @param:
     * @return:
     * @auther: ww
     * @date: 2020/3/13 0013 15:42
     */
    @Override
    public List getregionIdList(Integer regionId) {
        return hotFrontDeviceEXTMapper.getregionIdList(regionId);
    }

}
