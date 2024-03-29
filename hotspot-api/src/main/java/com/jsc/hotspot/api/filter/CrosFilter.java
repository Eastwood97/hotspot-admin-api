package com.jsc.hotspot.api.filter;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.CorsFilter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author huixing
 * @description 过滤器
 * @date 2019/11/7
 */
@Configuration
public class CrosFilter implements Filter {
    final static org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(CorsFilter.class);


    /**
     * 过滤
     * @param req
     * @param res
     * @param chain
     * @throws IOException
     * @throws ServletException
     */
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        HttpServletResponse response = (HttpServletResponse) res;
        HttpServletRequest request = (HttpServletRequest) req;
        //跨域设置,谁来都放行,与设置成*效果相同,但是这里设置成*行不通,因此用该方法代替
//        response.setHeader("Access-Control-Allow-Origin", "http://localhost:9527");
        response.setHeader("Access-Control-Allow-Origin", request.getHeader("Origin"));
        response.setHeader("Access-Control-Allow-Credentials", "true");
        //不能设置成*,否则跨域请求会失败
        response.setHeader("Access-Control-Allow-Methods", "POST,PUT, GET, OPTIONS, DELETE");
        response.setHeader("Access-Control-Max-Age", "3600");
        //我这里需要放行这三个header头部字段
        response.setHeader("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept, x-litemall-admin-token");

        try {
            chain.doFilter(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void init(FilterConfig filterConfig) {
    }

    public void destroy() {
    }
}
