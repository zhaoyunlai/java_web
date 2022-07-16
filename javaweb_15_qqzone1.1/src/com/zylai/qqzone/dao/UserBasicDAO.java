package com.zylai.qqzone.dao;

import com.zylai.qqzone.pojo.UserBasic;

import java.util.List;

/**
 * @Author: Zhao YunLai
 * @Date: 2022/07/15/14:30
 * @Description:
 */
public interface UserBasicDAO {
    //根据账号和密码获取特定用户信息
    UserBasic getUserBasic(String loginId,String pwd);
    //获取指定用户的所有好友
    List<UserBasic> getUserBasicList(UserBasic userBasic);
    //根据id查询
    UserBasic getUserBasicById(Integer id);
}
