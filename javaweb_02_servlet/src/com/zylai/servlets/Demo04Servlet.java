package com.zylai.servlets;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author: Zhao YunLai
 * @Date: 2022/06/25/12:02
 * @Description: 演示向HttpSession保存数据
 */
public class Demo04Servlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse resp) throws ServletException, IOException {
        request.getSession().setAttribute("uname","lina");
    }
}
