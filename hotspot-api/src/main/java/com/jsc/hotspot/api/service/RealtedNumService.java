package com.jsc.hotspot.api.service;

import com.jsc.hotspot.db.domain.RelatedNumResult;

import java.util.List;

/**
 * @author tzm
 * @desc 处理人脸识别与号码管理的业务的相关业务
 */
public interface RealtedNumService {
    /**
     * 分页查询
     * @param page
     * @param limit
     * @param targetName
     * @return
     */
    List<RelatedNumResult>  query(Integer page, Integer limit,String targetName);

    /**
     * 批量删除
     * @param ids
     * @return
     */
    boolean deleteById(String ids);
}
