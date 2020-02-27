package com.jsc.hotspot.api.config;

import com.jsc.hotspot.api.filter.CrosFilter;
import com.jsc.hotspot.api.filter.ShiroBasicHttpAuthenticationFilter;
import com.jsc.hotspot.api.shiro.AdminAuthorizingRealm;
import com.jsc.hotspot.api.shiro.AdminWebSessionManager;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.session.mgt.SessionManager;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

import javax.servlet.Filter;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 功能描述：在ShiroConfig中做什么事情呢？
 * 1.配置shiro安全管理器，向安全管理器中注入Realm域
 * 2.配置密码比较器
 * 3.配置拦截路径和放行路径
 */
@Configuration
public class ShiroConfig {


    @Bean
    public SessionManager sessionManager() {

        return new AdminWebSessionManager();
    }

    /**
     * 配置安全管理器，并且注入Realm域
     * @param
     * @return
     */
    @Bean
    public DefaultWebSecurityManager securityManager(){
        DefaultWebSecurityManager securityManager =new DefaultWebSecurityManager();
        securityManager.setRealm(realm());
        securityManager.setSessionManager(sessionManager());
        return  securityManager;
    }

    /**
     * 配置Realm域，注入自定义登录验证
     * @return
     */
    @Bean
    public Realm realm(){
        return new AdminAuthorizingRealm();
    }

    /**
     * 配置拦截路径和放行路径
     * @param securityManager
     * @return
     */
    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(SecurityManager securityManager){
        //shiro过滤器工厂类
        ShiroFilterFactoryBean shiroFilterFactoryBean=new ShiroFilterFactoryBean();
        //必须设置SercuityMannage
        shiroFilterFactoryBean.setSecurityManager((SecurityManager) securityManager);
        //拦截器--map集合
        Map<String,String> filterChainDefinitionMap =new LinkedHashMap<>();
        Map<String, Filter> filterMap = new HashMap<String, Filter>(1);
        filterMap.put("authc", new ShiroBasicHttpAuthenticationFilter());
//        filterChainDefinitionMap.put("/admin/auth/login","anon");
//        filterChainDefinitionMap.put("/admin/auth/401","anon");
//        filterChainDefinitionMap.put("/admin/auth/index","anon");
//        filterChainDefinitionMap.put("admin/auth/403","anon");
//        filterChainDefinitionMap.put("/admin/index/index","anon");
        filterChainDefinitionMap.put("/admin/auth/info","anon");
        shiroFilterFactoryBean.setFilters(filterMap);

        filterChainDefinitionMap.put("/admin/auth/login", "anon");
        filterChainDefinitionMap.put("/admin/auth/401", "anon");
        filterChainDefinitionMap.put("/admin/auth/index", "anon");
        filterChainDefinitionMap.put("/admin/auth/403", "anon");
        filterChainDefinitionMap.put("/admin/index/index", "anon");
        filterChainDefinitionMap.put("/admin/targetNum/exportTarget", "anon");
        filterChainDefinitionMap.put("/admin/**", "authc");
        filterChainDefinitionMap.put("/websocket", "anon");
        shiroFilterFactoryBean.setLoginUrl("/admin/auth/401");
        shiroFilterFactoryBean.setSuccessUrl("/admin/auth/index");
        shiroFilterFactoryBean.setUnauthorizedUrl("/admin/auth/403");
        /**
         * 匹配所有的路径，通过Map集合组成了一个拦截器链，自定向下过滤，一旦匹配则不再执行下面的过滤
         * 如果下面的定义与上面的冲突，那就按照谁先定义了谁说了算
         * /** 一定要配置在最后
         */
//        filterChainDefinitionMap.put("/admin/**","authc");
//        //将拦截器设置到shiro中
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
//        //设置登陆页，默认为Web工程根目录下的"/login.jsp"页面
//        shiroFilterFactoryBean.setLoginUrl("/admin/auth/401");
//        //设置登录成功后跳转的页面
//        shiroFilterFactoryBean.setSuccessUrl("admin/auth/index");
//        //未授权界面
//        shiroFilterFactoryBean.setUnauthorizedUrl("/admin/auth");

        return shiroFilterFactoryBean;
    }



    /**
     * 开启Shiro的注解(如@RequiresRoles,@RequiresPermissions),需借助SpringAOP扫描使用Shiro注解的类,并在必要时进行安全逻辑验证
     * 配置以下两个bean(DefaultAdvisorAutoProxyCreator(可选)和AuthorizationAttributeSourceAdvisor)即可实现此功能
     * 1.匹配所有类
     * 2.匹配所有加认证注解的方法
     * @param securityManager
     * @return
     */
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager){
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor=
                new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager((SecurityManager) securityManager);
        return authorizationAttributeSourceAdvisor;
    }

    @Bean("lifecycleBeanPostProcessor")
    public LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {
        return new LifecycleBeanPostProcessor();
    }


    @Bean
    @DependsOn("lifecycleBeanPostProcessor")
    public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator(){
        DefaultAdvisorAutoProxyCreator creator=new DefaultAdvisorAutoProxyCreator();
        creator.setProxyTargetClass(true);
        return creator;
    }

    @Bean
    public ShiroBasicHttpAuthenticationFilter shiroBasicHttpAuthenticationFilter() {
        return new ShiroBasicHttpAuthenticationFilter();
    }

}
