package com.jsc.hotspot.api;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class}, scanBasePackages = {"com.jsc.hotspot.db", "com.jsc.hotspot.common","com.jsc.hotspot.api"})
@MapperScan("com.jsc.hotspot.db.dao")
@EnableTransactionManagement
@EnableScheduling
@EnableCaching
public class HotspotApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(HotspotApiApplication.class, args);
    }

}
