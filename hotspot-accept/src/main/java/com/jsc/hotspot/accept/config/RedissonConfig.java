package com.jsc.hotspot.accept.config;//package com.jsc.hotspot.accept.config;
//
//import org.redisson.Redisson;
//import org.redisson.api.RedissonClient;
//import org.redisson.config.Config;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//import java.io.IOException;
//
///**
// * @author huixing
// * @description Redission可作为分布式锁
// * @date 2019/11/14
// */
////@Configuration
//public class RedissonConfig {
//
//    @Bean(destroyMethod = "shutdown")
//    RedissonClient redisson() throws IOException {
//        Config config = new Config();
//        //config.useClusterServers().addNodeAddress("127.0.0.1:6379");
//        config.useSingleServer().setAddress("redis://127.0.0.1:6379");
//        return Redisson.create(config);
//    }
//}
//
