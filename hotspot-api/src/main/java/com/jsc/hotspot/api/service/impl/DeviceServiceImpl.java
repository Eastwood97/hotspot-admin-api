package com.jsc.hotspot.api.service.impl;

import com.github.pagehelper.PageHelper;
import com.jsc.hotspot.api.service.DeviceService;
import com.jsc.hotspot.db.dao.HotFrontDeviceMapper;
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

@Service("DeviceService")
public class DeviceServiceImpl implements DeviceService {

    @Autowired
    HotFrontDeviceMapper hotFrontDeviceMapper;
    public void  add(HotFrontDevice hotFrontDevice){
        hotFrontDevice.setCreateTime(LocalDateTime.now());
        hotFrontDevice.setUpdateTime(LocalDateTime.now());
        hotFrontDeviceMapper.insertSelective(hotFrontDevice);
    }

    @Override
    public boolean deleteById(String devIds) {
        return false;
    }

    @Override
    public int updateById(HotFrontDevice hotFrontDevice) {
        hotFrontDevice.setUpdateTime(LocalDateTime.now());
        return  hotFrontDeviceMapper.updateByPrimaryKeySelective(hotFrontDevice);
    }

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

    @Override
    public HotFrontDevice getDeviceByDevName(String devName) {
        HotFrontDeviceExample hotFrontDeviceExample = new HotFrontDeviceExample();
        HotFrontDeviceExample.Criteria criteria = hotFrontDeviceExample.createCriteria();
        criteria.andDevNameEqualTo(devName);
        HotFrontDevice hotFrontDevice = hotFrontDeviceMapper.selectOneByExampleSelective(hotFrontDeviceExample);
        return hotFrontDevice;
    }
}
