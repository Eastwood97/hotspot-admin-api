package com.jsc.hotspot.api.service.impl;

import com.github.pagehelper.PageHelper;
import com.jsc.hotspot.api.service.TargetNumService;
import com.jsc.hotspot.db.dao.HotTargetInfoMapper;
import com.jsc.hotspot.db.dao.ext.HotTargetInfoEXTMapper;
import com.jsc.hotspot.db.domain.Admin;
import com.jsc.hotspot.db.domain.HotTargetInfo;
import com.jsc.hotspot.db.domain.HotTargetInfoExample;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.util.List;

@Service("TargetNumService")
@Transactional
public class TargetNumServiceImpl implements TargetNumService {

    @Autowired
    private HotTargetInfoMapper hotTargetInfoMapper;

    @Autowired
    private HotTargetInfoEXTMapper hotTargetInfoEXTMapper;

    private HotTargetInfo.Column[] columns=new HotTargetInfo.Column[]{
            HotTargetInfo.Column.targetId, HotTargetInfo.Column.targetName, HotTargetInfo.Column.imsi,
            HotTargetInfo.Column.imei,HotTargetInfo.Column.isdn, HotTargetInfo.Column.createTime,HotTargetInfo.Column.updateTime,
            HotTargetInfo.Column.desc,HotTargetInfo.Column.operatorId
    };

    @Override
    public List<HotTargetInfo> query(Integer page, Integer limit,String targetName,String imsi,String imei,String isdn ) {
        Subject currentUser = SecurityUtils.getSubject();
        Admin admin = (Admin) currentUser.getPrincipal();
        HotTargetInfoExample example=new HotTargetInfoExample();
        HotTargetInfoExample.Criteria criteria=example.createCriteria();
        if (!StringUtils.isEmpty(targetName)) {
            criteria.andTargetNameLike(targetName+"%");

        }
        if (!StringUtils.isEmpty(imsi)) {
            criteria.andImsiEqualTo(imsi);
        }
        if (!StringUtils.isEmpty(imei)) {
            criteria.andImeiEqualTo(imei);
        }
        if (!StringUtils.isEmpty(isdn)) {
            criteria.andIsdnEqualTo(isdn);
        }

        if(!"admin123".equals(admin.getUsername())){
            criteria.andOperatorIdEqualTo(admin.getId());
        }
       example.setOrderByClause("create_time desc");
        PageHelper.startPage(page, limit);
        return hotTargetInfoMapper.selectByExample(example);
    }

    @Override
    public int updateById(HotTargetInfo targetInfo) {
        targetInfo.setUpdateTime(LocalDateTime.now());
        return hotTargetInfoMapper.updateByPrimaryKeySelective(targetInfo);
    }

    @Override
    public boolean deleteById(String targetIds) {
        String[] spllit=targetIds.split(",");
        int result=hotTargetInfoEXTMapper.deleteNumById(spllit);
        return result==spllit.length;
    }

    @Override
    public void add(HotTargetInfo targetInfo) {
        Subject currentUser = SecurityUtils.getSubject();
        Admin admin = (Admin) currentUser.getPrincipal();
        targetInfo.setOperatorId(admin.getId());
        targetInfo.setCreateTime(LocalDateTime.now());
        targetInfo.setUpdateTime(LocalDateTime.now());
        hotTargetInfoMapper.insertSelective(targetInfo);
    }

    @Override
    public List<HotTargetInfo> getAllTargetNum() {
        HotTargetInfoExample example=new HotTargetInfoExample();
        return hotTargetInfoMapper.selectByExampleSelective(example);
    }

    public boolean insertForeach(List<HotTargetInfo> targetList){
        int result=hotTargetInfoEXTMapper.insertForeach(targetList);
        if(result==targetList.size()){
            return true;
        }else {
            return false;
        }

    }


}
