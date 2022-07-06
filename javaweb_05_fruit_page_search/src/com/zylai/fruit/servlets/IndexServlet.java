package com.zylai.fruit.servlets;

import com.zylai.fruit.dao.FruitDAO;
import com.zylai.fruit.dao.impl.FruitDAOImpl;
import com.zylai.fruit.pojo.Fruit;
import com.zylai.myssm.myspringmvc.ViewBaseServlet;
import com.zylai.myssm.util.StringUtil;

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
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        doGet(request,response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        int pageNo = 1;
        HttpSession session = request.getSession();

        String operate = request.getParameter("operate");
        // 如果operate不是空的，说明是通过表单的查询按钮过来的；否则就不是
        String keyword = null;
        if(StringUtil.isNotEmpty(operate) && "search".equals(operate)){
            //说明是通过表单查询发送的请求，此时pageNo应该还原为1，keyWord从请求参数中获取
            pageNo = 1;
            keyword = request.getParameter("keyword");
            if(StringUtil.isEmpty(keyword)){
                keyword="";
            }
//            将keyword保存到session中，因为，这次是根据表单的按钮查询的，但是之后的翻页操作，是在当前的关键字下进行的
            session.setAttribute("keyword",keyword);
        }else{
            //说明此处不是点击查询表单发过来的请求（比如点击下面的下一页上一页等，或者直接通过地址）
            //此时的keyWord应该从session作用域获取
            String pageNoStr = request.getParameter("pageNo");
            if(StringUtil.isNotEmpty(pageNoStr)){
                pageNo = Integer.parseInt(pageNoStr);
            }
            Object keywordObj = session.getAttribute("keyword");
            if(keywordObj!=null){
                keyword=(String)keywordObj;
            }else{
                keyword="";
            }
        }


//        将页码保存到session作用域中
        session.setAttribute("pageNo",pageNo);

        FruitDAO fruitDAO = new FruitDAOImpl();
        List<Fruit> fruitList = fruitDAO.getFruitPageList(keyword,pageNo);

//        总记录条数
        long fruitCount = fruitDAO.getFruitCount(keyword);
//        总页数
        int pageCount = (int)((fruitCount+4)/5);
        /*
        总记录条数和总页数的关系：
        总记录条数    总页数
        1               1
        5               1
        6               2
        10              2
        11              3

        所以关系应该是 (fruitCount+4)/5
         */
//        保存到session作用域中
        session.setAttribute("fruitList",fruitList);
        session.setAttribute("pageCount",pageCount);
//        此处的视图名称是index，thymeleaf会将这个逻辑视图名称
//        对应到物理视图名称上去
//        逻辑视图名称： index
//        物理视图名称： view-prefix + 逻辑视图名称 + view-suffix
//        所以真实的视图名称是：/index.html
        super.processTemplate("index",request,response);
    }
}
