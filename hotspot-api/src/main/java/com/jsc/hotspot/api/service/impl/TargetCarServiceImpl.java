package com.jsc.hotspot.api.service.impl;

import com.github.pagehelper.PageHelper;
import com.jsc.hotspot.api.service.TargetCarService;
import com.jsc.hotspot.common.util.StringUtil;
import com.jsc.hotspot.db.dao.HotTargetCarMapper;
import com.jsc.hotspot.db.dao.ext.TargetCarEXTMapper;
import com.jsc.hotspot.db.domain.Admin;
import com.jsc.hotspot.db.domain.HotTargetCar;
import com.jsc.hotspot.db.domain.HotTargetCarExample;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author tzm
 * @desc 处理目标车牌的相关业务
 */
@Service("TargetCarService")
public class TargetCarServiceImpl implements TargetCarService {

    @Autowired
    private HotTargetCarMapper hotTargetCarMapper;

    @Autowired
    private TargetCarEXTMapper targetCarEXTMapper;

    /**
     * 查询布控的车牌
     * @param plateNumber 车牌号
     * @param page
     * @param limit
     * @return
     */
    @Override
    public List<HotTargetCar> query(String plateNumber, Integer page, Integer limit) {
        HotTargetCarExample example=new HotTargetCarExample();
        HotTargetCarExample.Criteria criteria=example.createCriteria();
        if(!StringUtils.isEmpty(plateNumber)){
            criteria.andPlateNumberLike(plateNumber+"%");
        }
        PageHelper.startPage(page,limit);
        return hotTargetCarMapper.selectByExampleSelective(example);
    }

    /**
     * 添加目标车牌
     * @param hotTargetCar
     */
    @Override
    public void add(HotTargetCar hotTargetCar) {
        Subject currentUser = SecurityUtils.getSubject();
        Admin admin = (Admin) currentUser.getPrincipal();
        hotTargetCar.setCreateTime(LocalDateTime.now());
        hotTargetCar.setUpdateTime(LocalDateTime.now());
        hotTargetCar.setOperatorId(admin.getId());
        hotTargetCarMapper.insertSelective(hotTargetCar);
    }

    /**
     * 根据车牌查出一条信息
     * @param plateNumber
     * @return
     */
    @Override
    public HotTargetCar findByNumber(String plateNumber) {
        HotTargetCarExample example=new HotTargetCarExample();
        HotTargetCarExample.Criteria criteria=example.createCriteria();
        criteria.andPlateNumberEqualTo(plateNumber);
        return hotTargetCarMapper.selectByExampleSelective(example).get(0);
    }

    /**
     * 修改一条布控信息
     * @param hotTargetCar
     * @return
     */
    @Override
    public int update(HotTargetCar hotTargetCar) {
        hotTargetCar.setUpdateTime(LocalDateTime.now());
        return hotTargetCarMapper.updateByPrimaryKeySelective(hotTargetCar);
    }

    /**
     * 批量删除
     * @param targetIds
     * @return
     */
    @Override
    public boolean deleteById(String targetIds) {
        String[] split=targetIds.split(",");
        int result=targetCarEXTMapper.deleteById(split);
        return split.length==result;
    }
}
