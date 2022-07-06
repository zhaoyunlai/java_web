package com.zylai.servlets;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @Author: Zhao YunLai
 * @Date: 2022/06/25/11:26
 * @Description: session
 */
public class Demo03Servlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//       这段代码的意思就是，去获取请求的session，如果获取不到就会创建一个新的session。
        HttpSession session = req.getSession();
        System.out.println("session ID:"+session.getId());
    }
}
