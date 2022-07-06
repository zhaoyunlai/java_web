package com.zylai.fruit.controller;

import com.zylai.fruit.dao.UserDAO;
import com.zylai.fruit.dao.impl.UserDAOImpl;
import com.zylai.fruit.pojo.User;

import java.util.List;
import java.util.Scanner;

/**
 * @Author: Zhao YunLai
 * @Date: 2022/06/22/11:54
 * @Description: 测试使用
 */
public class UserController {
    private UserDAO userDAO = new UserDAOImpl();
    Scanner input = new Scanner(System.in);
    public void addUser(){
        System.out.print("请输入用户姓名：");
        String name = input.next();
        System.out.print("请输入用户年龄：");
        int age = input.nextInt();
        boolean b = userDAO.addUser(new User(0, name, age));
        if(b){
            System.out.println("添加成功");
        }else{
            System.out.println("添加失败！！！");
        }
    }

    public void selectAllUser(){
        List<User> userList = userDAO.getUserList();
        for (User user : userList) {
            System.out.println(user);
        }
    }
}
