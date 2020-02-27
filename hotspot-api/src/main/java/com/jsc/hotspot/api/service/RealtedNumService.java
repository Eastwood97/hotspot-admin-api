package com.jsc.hotspot.api.service;

import com.jsc.hotspot.db.domain.RelatedNumResult;

import java.util.List;

/**
 * @author  tzm
 *
 */
public interface RealtedNumService {
    List<RelatedNumResult>  query(Integer page, Integer limit, String targetName);

    boolean deleteById(String ids);
}
