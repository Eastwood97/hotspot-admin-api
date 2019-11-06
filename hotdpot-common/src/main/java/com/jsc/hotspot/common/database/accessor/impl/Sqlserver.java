package com.jsc.hotspot.common.database.accessor.impl;

import com.jsc.hotspot.common.database.accessor.AbstractDaoSupport;
import com.jsc.hotspot.common.database.datasource.CommonDataSource;
import org.springframework.stereotype.Component;

/**
 * Sqlserver JDBC Template
 */
@Component
public class Sqlserver extends AbstractDaoSupport {

    /**
     * 构造函数
     *
     * @param dataSource 数据源
     */
    public Sqlserver(CommonDataSource dataSource) {
        super(dataSource);
    }

}
