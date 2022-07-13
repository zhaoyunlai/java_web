package com.zylai.fruit.controller;

import com.zylai.fruit.biz.FruitService;
import com.zylai.fruit.biz.impl.FruitServiceImpl;
import com.zylai.fruit.pojo.Fruit;
import com.zylai.myssm.util.StringUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @Author: Zhao YunLai
 * @Date: 2022/07/06/22:29
 * @Description: 改进之后，这个controller只需要进行业务操作，不需要去管获取参数和资源的跳转。
 * 这些都由DispatcherServlet统一管理
 */

public class FruitController{

    private FruitService fruitService = new FruitServiceImpl();

    private String update(Integer fid,String fname,Integer price,Integer fcount,String remark) {

        fruitService.updateFruit(new Fruit(fid,fname,price,fcount,remark));
        return "redirect:fruit.do";
    }

    private String edit(Integer fid,HttpServletRequest request){
        if(fid!=null){
            Fruit fruit = fruitService.getFruitById(fid);
            request.setAttribute("fruit",fruit);
            return "edit";
//            super.processTemplate("edit",request,response);
        }
        return "error";
    }

    private String del(Integer fid){
        if(fid!=null){
            fruitService.delFruit(fid);
           return "redirect:fruit.do";
        }
        return "error";
    }


    private String add(String fname,Integer price,Integer fcount,String remark) {
//        3.执行插入
        fruitService.addFruit(new Fruit(0,fname,price,fcount,remark));

        return "redirect:fruit.do";
    }

    private String index(String oper,String keyword,Integer pageNo,HttpServletRequest request)  {

        HttpSession session = request.getSession();

        if(pageNo==null){
            pageNo=1;
        }

        if(StringUtil.isNotEmpty(oper) && "search".equals(oper)){
            //说明是通过表单查询发送的请求，此时pageNo应该还原为1，keyWord从请求参数中获取
            pageNo = 1;
            if(StringUtil.isEmpty(keyword)){
                keyword="";
            }
//            将keyword保存到session中，因为，这次是根据表单的按钮查询的，但是之后的翻页操作，是在当前的关键字下进行的
            session.setAttribute("keyword",keyword);
        }else{
            if(keyword==null){
                keyword="";
            }
        }

//        将页码保存到session作用域中
        session.setAttribute("pageNo",pageNo);

        List<Fruit> fruitList = fruitService.getFruitList(keyword,pageNo);

//        总页数
        int pageCount = fruitService.getPageCount(keyword);
        session.setAttribute("fruitList",fruitList);
        session.setAttribute("pageCount",pageCount);
        return "index";
    }
}
