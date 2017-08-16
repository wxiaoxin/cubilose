package com.cubilose.weixin.filter;

import org.springframework.context.annotation.Configuration;

import javax.servlet.*;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by King on 2017/2/14.
 *
 * 跨域
 */

@Configuration
public class CrosFilter implements Filter{


    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletResponse resp = (HttpServletResponse) response;
        // 允许跨域请求
        resp.setHeader("Access-Control-Allow-Origin", "*");
        chain.doFilter(request, resp);
    }

    @Override
    public void destroy() {

    }
}
