package com.zylai.myssm.filters;

import com.zylai.myssm.util.StringUtil;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * @Author: Zhao YunLai
 * @Date: 2022/07/13/18:43
 * @Description:
 */
@WebFilter(urlPatterns = {"*.do"},initParams = {
        @WebInitParam(name = "encoding",value = "UTF-8")
})
public class CharacterEncodingFilter implements Filter {

    private String encoding = "UTF-8";

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        String encodingStr = filterConfig.getInitParameter("encoding");
        if(StringUtil.isNotEmpty(encodingStr)){
            encoding=encodingStr;
        }
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        //设置编码
        ((HttpServletRequest)servletRequest).setCharacterEncoding(encoding);
        //放行
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {

    }
}
