package com.jsc.hotspot.accept.facade.impl;//package com.jsc.hotspot.accept.facade.impl;
//
//import org.springframework.cache.annotation.Cacheable;
//import org.springframework.data.redis.core.StringRedisTemplate;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
//import javax.annotation.Resource;
//
///**
// * @author huixing
// * @description
// * @date 2019/11/14
// */
//@Transactional
//@Service
//public class ReImpl implements RedisService {
//
//    @Resource
//    private CustomerRepo customerRepo;
//    @Resource
//    private StringRedisTemplate stringRedisTemplate;
//
//    public static final String REDIS_CUSTOMERS_ONE = "Customers";
//
//    public static final String REDIS_CUSTOMERS_ALL = "allList";
//
//    // =====================================================================使用Spring cahce 注解方式实现缓存
//    // ==================================单个操作
//
//    @Override
//    @Cacheable(value = "cache:customer", unless = "null == #result",key = "#id")
//    public CustomersEntity cacheOne(Integer id) {
//        final Optional<CustomersEntity> byId = customerRepo.findById(id);
//        return byId.isPresent() ? byId.get() : null;
//    }
//
//    @Override
//    @Cacheable(value = "cache:customer", unless = "null == #result", key = "#id")
//    public CustomersEntity cacheOne2(Integer id) {
//        final Optional<CustomersEntity> byId = customerRepo.findById(id);
//        return byId.isPresent() ? byId.get() : null;
//    }
//
//    //
//    @Override
//    @Cacheable(value = "cache:customer", unless = "null == #result", key = "#root.methodName + '.' + #id")
//    public CustomersEntity cacheOne3(Integer id) {
//        final Optional<CustomersEntity> byId = customerRepo.findById(id);
//        return byId.isPresent() ? byId.get() : null;
//    }
//
//    //
//    @Override
//    @Cacheable(value = "cache:customer", unless = "null == #result", key = "#root.methodName + '.' + #id")
//    public String cacheOne4(Integer id) {
//        final Optional<CustomersEntity> byId = customerRepo.findById(id);
//        return byId.map(JSONUtil::toJsonStr).orElse(null);
//    }
//
//    //
//    @Override
//    @Cacheable(value = "cache:customer", unless = "null == #result", key = "#root.methodName + '.' + #id")
//    public CustomersEntity cacheOne5(Integer id) {
//        Optional<CustomersEntity> byId = customerRepo.findById(id);
//        return byId.filter(obj -> !StrUtil.isBlankIfStr(obj)).orElse(null);
//    }
//
//
//
//    // ==================================删除缓存
//    @Override
//    @CacheEvict(value = "cache:customer", key = "'cacheOne5' + '.' + #id")
//    public Object del(Integer id) {
//        // 删除缓存后的逻辑
//        return null;
//    }
//
//    @Override
//    @CacheEvict(value = "cache:customer",allEntries = true)
//    public void del() {
//
//    }
//
//    @CacheEvict(value = "cache:all",allEntries = true)
//    public void delall() {
//
//    }
//    // ==================List操作
//
//    @Override
//    @Cacheable(value = "cache:all")
//    public List<CustomersEntity> cacheList() {
//        List<CustomersEntity> all = customerRepo.findAll();
//        return all;
//    }
//
//    // todo 先查询缓存，再校验是否一致，然后更新操作，比较实用，要清楚缓存的数据格式（明确业务和缓存模型数据）
//    @Override
//    @CachePut(value = "cache:all",unless = "null == #result",key = "#root.methodName")
//    public List<CustomersEntity> cacheList2() {
//        List<CustomersEntity> all = customerRepo.findAll();
//        return all;
//    }
//
//}
//
//
//
