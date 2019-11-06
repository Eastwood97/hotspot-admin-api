package com.jsc.hotspot.common.database.accessor.impl;

import com.jsc.hotspot.common.database.accessor.AbstractDaoSupport;
import com.jsc.hotspot.common.database.datasource.CommonDataSource;
import org.springframework.stereotype.Component;

/**
 * Cassandra JDBC Template
 */
@Component
public class Cassandra extends AbstractDaoSupport {

    /**
     * 构造函数
     *
     * @param dataSource 数据源
     */
    public Cassandra(CommonDataSource dataSource) {
        super(dataSource);
    }

}
