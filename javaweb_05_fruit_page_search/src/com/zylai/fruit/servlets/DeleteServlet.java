package com.zylai.fruit.servlets;

import com.zylai.fruit.dao.FruitDAO;
import com.zylai.fruit.dao.impl.FruitDAOImpl;
import com.zylai.myssm.myspringmvc.ViewBaseServlet;
import com.zylai.myssm.util.StringUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author: Zhao YunLai
 * @Date: 2022/06/26/12:30
 * @Description:
 */
@WebServlet("/del.do")
public class DeleteServlet extends ViewBaseServlet {

    private FruitDAO fruitDAO = new FruitDAOImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String fidStr = request.getParameter("fid");
        if(StringUtil.isNotEmpty(fidStr)){
            int fid = Integer.parseInt(fidStr);
            fruitDAO.deleteFruitByFid(fid);

            response.sendRedirect("fruit_index");
        }
    }
}
