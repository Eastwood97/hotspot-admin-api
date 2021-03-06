package com.jsc.hotspot.api.service.impl;

import com.github.pagehelper.PageHelper;
import com.jsc.hotspot.api.service.RealtedNumService;
import com.jsc.hotspot.db.dao.RelatedNumResultMapper;
import com.jsc.hotspot.db.dao.ext.RelatedNumResultEXTMapper;
import com.jsc.hotspot.db.domain.RelatedNumResult;
import com.jsc.hotspot.db.domain.RelatedNumResultExample;
import org.apache.avro.data.Json;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author tzm
 * @desc 处理人脸识别与号码管理的业务的相关业务
 */
@Service
public class RelatedNumServiceImpl implements RealtedNumService {

    @Autowired
    private RelatedNumResultMapper relatedNumResultMapper;

    @Autowired
    private RelatedNumResultEXTMapper relatedNumResultEXTMapper;

    /**
     * @author tzm
     * @desc 处理人脸识别与号码管理的业务的相关业务
     */
    @Override
    public List<RelatedNumResult> query(Integer page, Integer limit,String targetName) {
        RelatedNumResultExample example=new RelatedNumResultExample();
        if (null!=targetName&&!targetName.equals("")){
            example.or().andTargetNameLike(targetName+"%");
        }
        PageHelper.startPage(page, limit);
         return  relatedNumResultMapper.selectByExampleSelective(example);
    }

    /**
     * 批量删除
     * @param ids
     * @return
     */
    @Override
    public boolean deleteById(String ids) {
        String[] split=ids.split(",");
        int result=relatedNumResultEXTMapper.deleteById(split);
        return split.length==result;
    }
}
