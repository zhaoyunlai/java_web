package com.zylai.filters;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

/**
 * @Author: Zhao YunLai
 * @Date: 2022/07/13/17:21
 * @Description:
 */
//@WebFilter("/demo01.do")
public class Demo01Filter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("hello A");
        //放行
        filterChain.doFilter(servletRequest,servletResponse);
        System.out.println("hello A2");
    }

    @Override
    public void destroy() {

    }

}
