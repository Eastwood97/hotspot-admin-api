package com.jsc.hotspot.api.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.jsc.hotspot.api.config.UrlConst;
import com.jsc.hotspot.api.dto.TargetFace;
import com.jsc.hotspot.api.service.TargetFaceService;
import com.jsc.hotspot.api.utils.HttpUtil;
import com.jsc.hotspot.common.bean.FileInfo;
import com.jsc.hotspot.db.dao.CameraTargetFaceMapper;
import com.jsc.hotspot.db.dao.ext.HotTargetFaceEXTMapper;
import com.jsc.hotspot.db.domain.Admin;
import com.jsc.hotspot.db.domain.CameraTargetFace;
import com.jsc.hotspot.db.domain.CameraTargetFaceExample;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.DeleteMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.RequestEntity;
import org.apache.commons.httpclient.methods.StringRequestEntity;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.client.utils.HttpClientUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author tzm
 * @desc 处理目标人脸的相关业务
 */
@Service("TargetFaceService")
public class TargetFaceServiceImpl implements TargetFaceService {

    private final Log logger = LogFactory.getLog(TargetFaceServiceImpl.class);

    @Autowired
    private CameraTargetFaceMapper targetFaceMapper;

    @Autowired
    private HotTargetFaceEXTMapper hotTargetFaceEXTMapper;

    /**
     * 添加目标
     * @param targetFace
     */
    @Override
    public void add(TargetFace targetFace) {
        CameraTargetFace cameraTargetFace =new CameraTargetFace();
        cameraTargetFace.setTargetName(targetFace.getTargetName());
        cameraTargetFace.setDesc(targetFace.getDesc());
        Map<String,String> map=new HashMap<String,String>();
        map.put("fileId1",targetFace.getFileId1());
//        map.put("fileId1", "file");
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
        // 创建HTTPClient实例对象
        String paramJSON = fileName;
        FileInfo fileInfo = new FileInfo();

        fileInfo.setUrl(JSON.toJSONString(map));
        fileInfo.setTargetName(targetFace.getTargetName());
        fileInfo.setDescribe(targetFace.getDesc());
        String response = HttpUtil.sendPost(JSON.toJSONString(fileInfo), UrlConst.SDK_URL);
        if (response != null){
            if (logger.isDebugEnabled()) {
                logger.debug("TargetFaceServiceImpl 通过Http请求进行布控：" + response);
            }
        }
        targetFaceMapper.insertSelective(cameraTargetFace);
    }


    /**
     * 批量删除
     * @param targetIds
     * @return
     */
    @Override
    public boolean deleteById(String targetIds) {
        String[] split=targetIds.split(",");
        int result=hotTargetFaceEXTMapper.deleteById(split);
        org.apache.commons.httpclient.HttpClient client = new org.apache.commons.httpclient.HttpClient();
        DeleteMethod method = new DeleteMethod("");
        method.setDoAuthentication(true);
//        try {
//            int statusCode = client.executeMethod(method);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        return split.length==result;
    }

    /**
     * 修改目标人脸的数据
     * @param targetFace
     * @return
     */
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

    /**
     * 分页查询
     * @param page
     * @param limit
     * @param targetName
     * @return
     */
    @Override
    public List<CameraTargetFace> getTargetFace(Integer page,Integer limit,String targetName) {
        Subject currentUser = SecurityUtils.getSubject();
        Admin admin = (Admin) currentUser.getPrincipal();
        CameraTargetFaceExample cameraTargetFaceExample=new CameraTargetFaceExample();
        CameraTargetFaceExample.Criteria criteria=cameraTargetFaceExample.createCriteria();
        if(!StringUtils.isEmpty(targetName)){
            criteria.andTargetNameLike("%"+targetName+"%");
        }
        if(!"admin123".equals(admin.getUsername())){
            criteria.andOperatorIdEqualTo(admin.getId());
        }
        PageHelper.startPage(page, limit);
        return targetFaceMapper.selectByExampleSelective(cameraTargetFaceExample);
    }
}
