package com.jsc.hotspot.api;

import com.jsc.hotspot.api.facade.impl.KafkaReceiverServiceImpl;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@MapperScan({"com.jsc.hotspot.db.dao", "com.jsc.hotspot.db.dao.ext"})
@ComponentScan(basePackages = {"com.jsc.hotspot.db", "com.jsc.hotspot.common", "com.jsc.hotspot.api"})
@EnableTransactionManagement
@EnableScheduling
@EnableCaching
@EnableAsync
public class HotspotApiApplication {
    public static void main(String[] args) {
        SpringApplication.run(HotspotApiApplication.class, args);
    }

}
