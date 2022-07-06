package com.zylai.dao.impl;

import com.zylai.dao.UserDAO;
import com.zylai.dao.base.BaseDAO;
import com.zylai.pojo.User;

import java.util.List;

/**
 * @Author: Zhao YunLai
 * @Date: 2022/06/22/22:12
 * @Description:
 */
public class UserDAOImpl extends BaseDAO<User> implements UserDAO {
    @Override
    public List<User> getUserList() {
        return super.executeQuery("select * from user");
    }

    @Override
    public User getUserByName(String name) {
        return super.load("select * from user where name=?",name);
    }

    @Override
    public boolean addUser(User user) {
        String sql = "insert into user values(0,?,?)";
        return  super.executeUpdate(sql,user.getName(),user.getAge())>0;
    }

    @Override
    public boolean deleteUserByName(String name) {
        String sql = "delete from user where name=?";
        return super.executeUpdate(sql,name)>0;
    }
}
