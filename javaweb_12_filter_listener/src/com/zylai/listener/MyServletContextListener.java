package com.zylai.listener;


import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 * @Author: Zhao YunLai
 * @Date: 2022/07/14/16:43
 * @Description:
 */
@WebListener
public class MyServletContextListener implements ServletContextListener{
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        System.out.println("Servlet上下文对象初始化对象动作被我监听到了......");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        System.out.println("Servlet上下文对象销毁的动作被我监听到了......");
    }
}
