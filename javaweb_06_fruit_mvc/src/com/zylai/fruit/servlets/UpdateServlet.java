package com.zylai.fruit.servlets;

import com.zylai.fruit.dao.FruitDAO;
import com.zylai.fruit.dao.impl.FruitDAOImpl;
import com.zylai.fruit.pojo.Fruit;
import com.zylai.myssm.myspringmvc.ViewBaseServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author: Zhao YunLai
 * @Date: 2022/06/26/11:54
 * @Description:
 */
@WebServlet("/update.do")
public class UpdateServlet extends ViewBaseServlet {

    private FruitDAO fruitDAO = new FruitDAOImpl();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

//        1.设置编码
        request.setCharacterEncoding("utf-8");
        //2.获取参数
        String fidStr = request.getParameter("fid");
        int fid = Integer.parseInt(fidStr);
        String fname = request.getParameter("fname");
        String priceStr = request.getParameter("price");
        int price = Integer.parseInt(priceStr);
        String fcountStr = request.getParameter("fcount");
        int fcount = Integer.parseInt(fcountStr);
        String remark = request.getParameter("remark");
        //3.执行更新
        fruitDAO.updateFruit(new Fruit(fid,fname,price,fcount,remark));

        //4.资源跳转
//        super.processTemplate("index",request,response);
//        相当于服务器端重定向
//        此处需要客户端重定向，目的是重新给indexServlet发请求，重新获取fruitList放到session中
//        这样首页的数据才会更新
        response.sendRedirect("fruit_index");
    }
}
