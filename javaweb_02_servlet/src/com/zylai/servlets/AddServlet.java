package com.zylai.servlets;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author: Zhao YunLai
 * @Date: 2022/06/23/10:41
 * @Description:
 */
public class AddServlet extends HttpServlet {
    /**
     *  从父类继承过来的，默认权限是protected，可以改成public
     * @param request 服务器端把请求封装成一个HttpServletRequest对象
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

//        post模式下设置编码，防止中文乱码
//        需要注意的是，设置编码必须在所有获取参数之前执行
        request.setCharacterEncoding("UTF-8");
//        get方式目前不需要设置编码（Tomcat8及其以后）

//        通过getParameter获取post请求中发送的值
        String fname = request.getParameter("fname");
        String priceStr = request.getParameter("price");
        Integer price = Integer.parseInt(priceStr);
        String fcountStr = request.getParameter("fcount");
        Integer fcount = Integer.parseInt(fcountStr);
        String remark = request.getParameter("remark");

        System.out.println("fname = "+fname);
        System.out.println("price = "+price);
        System.out.println("fcount = "+fcount);
        System.out.println("remark = "+remark);

    }
}
