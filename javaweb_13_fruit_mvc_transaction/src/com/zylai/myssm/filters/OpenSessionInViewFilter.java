package com.zylai.myssm.filters;

import com.zylai.myssm.trans.TransactionManager;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;
import java.sql.SQLException;

/**
 * @Author: Zhao YunLai
 * @Date: 2022/07/14/9:27
 * @Description: 事务的过滤器
 */
@WebFilter("*.do")
public class OpenSessionInViewFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        try{
            //开启事务
            System.out.println("开启事务...");
            TransactionManager.beginTrans();
            //放行
            filterChain.doFilter(servletRequest, servletResponse);
            //提交事务
            System.out.println("提交事务...");
            TransactionManager.commit();
        }catch (Exception e){
            e.printStackTrace();
            try {
                System.out.println("回滚事务...");
                TransactionManager.rollback();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }

    @Override
    public void destroy() {

    }
}
