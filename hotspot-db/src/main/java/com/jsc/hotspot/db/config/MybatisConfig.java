//package com.jsc.hotspot.db.config;
//
////import com.alibaba.druid.pool.DruidDataSource;
////import com.alibaba.druid.support.http.StatViewServlet;
////import com.alibaba.druid.support.http.WebStatFilter;
//import org.apache.ibatis.session.SqlSessionFactory;
//import org.mybatis.spring.SqlSessionFactoryBean;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
//import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
//import org.springframework.boot.context.properties.EnableConfigurationProperties;
//import org.springframework.boot.web.servlet.FilterRegistrationBean;
//import org.springframework.boot.web.servlet.ServletRegistrationBean;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//import javax.sql.DataSource;
//import java.sql.SQLException;
//
//
///**
// *   
// * <p>描述</p>
// *
// * @author: 彗星
// * @date: 2018/3/1 
// * @since V1.0
// *  
// */
////@Configuration
//public class MybatisConfig {
//
//
//    @Autowired
//    private DataSourceProperties dataSourceProperties;
//
//
//    @Bean(name = "dataSource")
//    public DataSource dataSource() {
//
//        DruidDataSource dataSource = new DruidDataSource();
//        dataSource.setUrl(dataSourceProperties.getUrl());
//        System.out.println(dataSourceProperties.getUrl());
//        dataSource.setDriverClassName(dataSourceProperties.getDriverClassName());
//        dataSource.setUsername(dataSourceProperties.getUsername());
//        dataSource.setPassword(dataSourceProperties.getPassword());
//
//        return dataSource;
//
//    }
//
//    /**
//     * 注册Servlet信息， 配置监控视图
//     *
//     * @return
//     */
////    @Bean
////    @ConditionalOnMissingBean
////    public ServletRegistrationBean druidServlet() {
////        ServletRegistrationBean servletRegistrationBean = new ServletRegistrationBean(new StatViewServlet(), "/druid/*");
////
////        //白名单：
////        servletRegistrationBean.addInitParameter("allow","192.168.6.195");
////        //IP黑名单 (存在共同时，deny优先于allow) : 如果满足deny的话提示:Sorry, you are not permitted to view this page.
////        servletRegistrationBean.addInitParameter("deny","192.168.6.73");
////        //登录查看信息的账号密码, 用于登录Druid监控后台
////        servletRegistrationBean.addInitParameter("loginUsername", "admin");
////        servletRegistrationBean.addInitParameter("loginPassword", "admin");
////        //是否能够重置数据.
////        servletRegistrationBean.addInitParameter("resetEnable", "true");
////        return servletRegistrationBean;
////
////    }
//
////    /**
////     * 注册Filter信息, 监控拦截器
////     *
////     * @return
////     */
////    @Bean
////    @ConditionalOnMissingBean
////    public FilterRegistrationBean filterRegistrationBean() {
////        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
////        filterRegistrationBean.setFilter(new WebStatFilter());
////        filterRegistrationBean.addUrlPatterns("/*");
////        filterRegistrationBean.addInitParameter("exclusions", "*.js,*.gif,*.jpg,*.png,*.css,*.ico,/druid/*");
////        return filterRegistrationBean;
////    }
//
//    @Bean
//    public SqlSessionFactory sqlSessionFactory() throws Exception {
//        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
//        sqlSessionFactoryBean.setDataSource(dataSource());
//        return sqlSessionFactoryBean.getObject();
//    }
//}
