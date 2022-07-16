package com.zylai.qqzone.service.impl;

import com.zylai.qqzone.dao.UserBasicDAO;
import com.zylai.qqzone.pojo.UserBasic;
import com.zylai.qqzone.service.UserBasicService;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: Zhao YunLai
 * @Date: 2022/07/15/15:02
 * @Description:
 */
public class UserBasicServiceImpl implements UserBasicService {
    private UserBasicDAO  userBasicDAO = null;
    @Override
    public UserBasic login(String loginId, String pwd) {
        UserBasic userBasic = userBasicDAO.getUserBasic(loginId, pwd);
        return userBasic;
    }

    @Override
    public List<UserBasic> getFriendList(UserBasic userBasic) {
        List<UserBasic> userBasicList = userBasicDAO.getUserBasicList(userBasic);
        List<UserBasic> friendList = new ArrayList<>(userBasicList.size());
        for (UserBasic basic : userBasicList) {
            friendList.add(userBasicDAO.getUserBasicById(basic.getId()));
        }
        return friendList;
    }

    @Override
    public UserBasic getUserBasicById(Integer id) {
        return userBasicDAO.getUserBasicById(id);
    }
}
