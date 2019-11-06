package com.jsc.hotspot.common.database.accessor.impl;

import com.jsc.hotspot.common.database.accessor.AbstractDaoSupport;
import com.jsc.hotspot.common.database.datasource.CommonDataSource;
import org.springframework.stereotype.Component;

/**
 * Sqlite JDBC Template
 */
@Component
public class Sqlite extends AbstractDaoSupport {

    /**
     * 构造函数
     *
     * @param dataSource 数据源
     */
    public Sqlite(CommonDataSource dataSource) {
        super(dataSource);
    }

}
