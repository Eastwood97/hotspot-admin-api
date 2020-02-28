package com.jsc.hotspot.api.service;

import java.io.InputStream;
import java.util.List;

/**
 * @author tzm
 * @desc 处理导入业务
 */
public interface ImportService {
    /**
     * excel表格导入数据
     * @param in
     * @param fileName
     * @return
     * @throws Exception
     */
    List getBankListByExcel(InputStream in, String fileName) throws Exception;
}
