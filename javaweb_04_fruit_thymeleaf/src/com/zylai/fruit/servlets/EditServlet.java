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
import java.io.IOException;

/**
 * @Author: Zhao YunLai
 * @Date: 2022/06/26/11:08
 * @Description:
 */
@WebServlet("/edit.do")
public class EditServlet extends ViewBaseServlet {

    private FruitDAO fruitDAO = new FruitDAOImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String fidStr = request.getParameter("fid");
        if(StringUtil.isNotEmpty(fidStr)){
            int fid = Integer.parseInt(fidStr);
            Fruit fruit = fruitDAO.getFruitByFid(fid);
            request.setAttribute("fruit",fruit);

            super.processTemplate("edit",request,response);
        }
    }
}
