package com.zylai.fruit.dao;

import com.zylai.fruit.pojo.User;

import java.util.List;

/**
 * @Author: Zhao YunLai
 * @Date: 2022/06/22/11:47
 * @Description:
 */
public interface UserDAO {
//    查询所有用户
    List<User> getUserList();
//    根据名称获取用户信息
    User getUserByName(String name);
//    添加用户
    boolean addUser(User user);
//    根据姓名删除用户
    boolean deleteUserByName(String name);
}
