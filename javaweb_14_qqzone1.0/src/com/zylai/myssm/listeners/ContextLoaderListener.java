package com.zylai.myssm.listeners;

import com.zylai.myssm.ioc.BeanFactory;
import com.zylai.myssm.ioc.ClassPathXmlApplication;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 * @Author: Zhao YunLai
 * @Date: 2022/07/14/16:56
 * @Description: 监听上下文启动，在上下文启动的时候创建IOC容器，然后将其保存到application作用域
 * 后面中央控制器再从application作用域中去获取IOC容器
 */
@WebListener
public class ContextLoaderListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        //1.获取servletContext对象
        ServletContext application = sce.getServletContext();
        //2.获取上下文初始化参数
        String path = application.getInitParameter("contextConfigLocation");
        //3.创建IOC容器
        BeanFactory beanFactory = new ClassPathXmlApplication(path);
        //4.将IOC容器保存到application作用域中
        application.setAttribute("beanFactory",beanFactory);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {

    }
}
