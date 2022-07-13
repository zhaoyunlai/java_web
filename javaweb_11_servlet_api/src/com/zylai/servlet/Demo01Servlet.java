package com.zylai.servlet;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author: Zhao YunLai
 * @Date: 2022/07/09/16:04
 * @Description:
 */
@WebServlet(
        initParams = {
                @WebInitParam(name = "hello1",value = "world1"),
                @WebInitParam(name="uname1",value="jack1")
        })
public class Demo01Servlet extends HttpServlet {

    @Override
    public void init() throws ServletException {
        ServletConfig config = getServletConfig();
        //在配置文件中配置初始的值
        String initValue = config.getInitParameter("hello");
        System.out.println("initValue = "+initValue);

        ServletContext servletContext = getServletContext();
        String contextConfigLocation = servletContext.getInitParameter("contextConfigLocation");
        System.out.println(contextConfigLocation);
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse resp) throws ServletException, IOException {

        request.getServletContext();
        request.getSession().getServletContext();
    }
}
