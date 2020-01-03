package com.jsc.hotspot.api.service.impl;

import com.github.pagehelper.PageHelper;
import com.jsc.hotspot.api.service.RealtedNumService;
import com.jsc.hotspot.db.dao.RelatedNumResultMapper;
import com.jsc.hotspot.db.domain.RelatedNumResult;
import com.jsc.hotspot.db.domain.RelatedNumResultExample;
import org.apache.avro.data.Json;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RelatedNumServiceImpl implements RealtedNumService {

    @Autowired
    private RelatedNumResultMapper relatedNumResultMapper;

    @Override
    public List<RelatedNumResult> query(Integer page, Integer limit,String targetName) {
        RelatedNumResultExample example=new RelatedNumResultExample();
        if (null!=targetName&&!targetName.equals("")){
            example.or().andTargetNameLike(targetName+"%");
        }
        PageHelper.startPage(page, limit);
         return  relatedNumResultMapper.selectByExampleSelective(example);
    }
}
