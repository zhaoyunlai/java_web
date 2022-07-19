package com.zylai.cookies;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author: Zhao YunLai
 * @Date: 2022/07/17/9:06
 * @Description:
 */
@WebServlet("/cookie01")
public class Demo01Cookie extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.创建cookie
        Cookie cookie = new Cookie("uname","jack");
        //2.将cookie保存到客户端中
        response.addCookie(cookie);
        //
        request.getRequestDispatcher("hello.html").forward(request,response);
    }
}
