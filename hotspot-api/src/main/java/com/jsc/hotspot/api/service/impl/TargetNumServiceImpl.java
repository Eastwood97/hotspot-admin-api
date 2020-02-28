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

/**
 * @author tzm
 * @desc 处理目标号码的相关业务
 */
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

    /**
     * 分页查询
     * @param page
     * @param limit
     * @param targetName
     * @param imsi
     * @param imei
     * @param isdn
     * @return
     */
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

    /**
     * 编辑布控名单
     * @param targetInfo
     * @return
     */
    @Override
    public int updateById(HotTargetInfo targetInfo) {
        targetInfo.setUpdateTime(LocalDateTime.now());
        return hotTargetInfoMapper.updateByPrimaryKeySelective(targetInfo);
    }

    /**
     * 批量删除
     * @param targetIds
     * @return
     */
    @Override
    public boolean deleteById(String targetIds) {
        String[] spllit=targetIds.split(",");
        int result=hotTargetInfoEXTMapper.deleteNumById(spllit);
        return result==spllit.length;
    }

    /**
     * 添加目标
     * @param targetInfo
     */
    @Override
    public void add(HotTargetInfo targetInfo) {
        Subject currentUser = SecurityUtils.getSubject();
        Admin admin = (Admin) currentUser.getPrincipal();
        targetInfo.setOperatorId(admin.getId());
        targetInfo.setCreateTime(LocalDateTime.now());
        targetInfo.setUpdateTime(LocalDateTime.now());
        hotTargetInfoMapper.insertSelective(targetInfo);
    }

    /**
     * 获取所有的目标信息
     * @return
     */
    @Override
    public List<HotTargetInfo> getAllTargetNum() {
        HotTargetInfoExample example=new HotTargetInfoExample();
        return hotTargetInfoMapper.selectByExampleSelective(example);
    }

    /**
     * 批量插入数据
     * @param targetList
     * @return
     */
    public boolean insertForeach(List<HotTargetInfo> targetList){
        int result=hotTargetInfoEXTMapper.insertForeach(targetList);
        if(result==targetList.size()){
            return true;
        }else {
            return false;
        }

    }


}
