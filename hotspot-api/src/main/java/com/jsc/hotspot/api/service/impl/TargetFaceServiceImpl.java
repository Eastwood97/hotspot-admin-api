package com.jsc.hotspot.api.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.jsc.hotspot.api.dto.TargetFace;
import com.jsc.hotspot.api.service.TargetFaceService;
import com.jsc.hotspot.db.dao.CameraTargetFaceMapper;
import com.jsc.hotspot.db.dao.ext.HotTargetFaceEXTMapper;
import com.jsc.hotspot.db.domain.Admin;
import com.jsc.hotspot.db.domain.CameraTargetFace;
import com.jsc.hotspot.db.domain.CameraTargetFaceExample;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("TargetFaceService")
public class TargetFaceServiceImpl implements TargetFaceService {
    @Autowired
    private CameraTargetFaceMapper targetFaceMapper;

    @Autowired
    private HotTargetFaceEXTMapper hotTargetFaceEXTMapper;

    @Override
    public void add(TargetFace targetFace) {
        CameraTargetFace cameraTargetFace =new CameraTargetFace();
        cameraTargetFace.setTargetName(targetFace.getTargetName());
        cameraTargetFace.setDesc(targetFace.getDesc());
        Map<String,String> map=new HashMap<String,String>();
        map.put("fileId1",targetFace.getFileId1());
        map.put("fileId2",targetFace.getFileId2());
        map.put("fileId3",targetFace.getFileId3());

        String fileName= JSON.toJSONString(map);
        JSONObject jsonObject=JSONObject.parseObject(fileName);
        cameraTargetFace.setFileName(jsonObject);
        cameraTargetFace.setCreateTime(LocalDateTime.now());
        cameraTargetFace.setUpdateTime(LocalDateTime.now());
        Subject currentUser = SecurityUtils.getSubject();
        Admin admin = (Admin) currentUser.getPrincipal();
        cameraTargetFace.setOperatorId(admin.getId());
        targetFaceMapper.insertSelective(cameraTargetFace);
    }

    @Override
    public boolean deleteById(String targetIds) {
        String[] split=targetIds.split(",");
        int result=hotTargetFaceEXTMapper.deleteById(split);
        return split.length==result;

    }

    @Override
    public int update(TargetFace targetFace) {
        CameraTargetFace cameraTargetFace =new CameraTargetFace();
        cameraTargetFace.setTargetId(targetFace.getTargetId());
        cameraTargetFace.setTargetName(targetFace.getTargetName());
        cameraTargetFace.setDesc(targetFace.getDesc());
        Map<String,String> map=new HashMap<String,String>();
        map.put("fileId1",targetFace.getFileId1());
        map.put("fileId2",targetFace.getFileId2());
        map.put("fileId3",targetFace.getFileId3());

        String fileName= JSON.toJSONString(map);
        JSONObject jsonObject=JSONObject.parseObject(fileName);
        cameraTargetFace.setFileName(jsonObject);
        cameraTargetFace.setUpdateTime(LocalDateTime.now());


        return targetFaceMapper.updateByPrimaryKeySelective(cameraTargetFace);

    }

    @Override
    public List<CameraTargetFace> getTargetFace(Integer page,Integer limit,String targetName) {
        CameraTargetFaceExample cameraTargetFaceExample=new CameraTargetFaceExample();
        CameraTargetFaceExample.Criteria criteria=cameraTargetFaceExample.createCriteria();
        if(!StringUtils.isEmpty(targetName)){
            criteria.andTargetNameLike("%"+targetName+"%");
        }
        PageHelper.startPage(page, limit);
        return targetFaceMapper.selectByExampleSelective(cameraTargetFaceExample);
    }
}
