package com.zylai.fruit.servlets;

import com.zylai.fruit.dao.FruitDAO;
import com.zylai.fruit.dao.impl.FruitDAOImpl;
import com.zylai.fruit.pojo.Fruit;
import com.zylai.myssm.myspringmvc.ViewBaseServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

/**
 * @Author: Zhao YunLai
 * @Date: 2022/06/25/18:02
 * @Description:
 */
//Servlet 从3.0开始支持注解方式的注册
@WebServlet("/fruit_index")
public class IndexServlet extends ViewBaseServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        FruitDAO fruitDAO = new FruitDAOImpl();
        List<Fruit> fruitList = fruitDAO.getFruitList();
//        保存到session作用域中
        HttpSession session = request.getSession();
        session.setAttribute("fruitList",fruitList);

//        此处的视图名称是index，thymeleaf会将这个逻辑视图名称
//        对应到物理视图名称上去
//        逻辑视图名称： index
//        物理视图名称： view-prefix + 逻辑视图名称 + view-suffix
//        所以真实的视图名称是：/index.html
        super.processTemplate("index",request,response);
    }
}
