package com.zylai.qqzone.controller;

import com.zylai.qqzone.pojo.Topic;
import com.zylai.qqzone.pojo.UserBasic;
import com.zylai.qqzone.service.TopicService;
import com.zylai.qqzone.service.UserBasicService;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @Author: Zhao YunLai
 * @Date: 2022/07/15/15:18
 * @Description:
 */
public class UserController {
    private UserBasicService userBasicService = null;

    private TopicService topicService = null;

    public String login(String loginId, String pwd, HttpSession session){

        //1. 登录验证
        UserBasic userBasic = userBasicService.login(loginId, pwd);
        if(userBasic!=null){
            // 获取相关好友信息
            List<UserBasic> friendList = userBasicService.getFriendList(userBasic);
            // 获取相关的日志列表信息
            List<Topic> topicList = topicService.getTopicList(userBasic);

            userBasic.setFriendList(friendList);
            userBasic.setTopicList(topicList);
            //userBasic这个key保存的是登陆者的信息
            session.setAttribute("userBasic",userBasic);
            //friend这个key保存的是当前进入的是谁的空间
            session.setAttribute("friend",userBasic);
            return "index";
        }
        return "login";
    }

    public String friend(Integer id,HttpSession session){
        //1.根据id获取指定用户信息
        UserBasic currentFriend = userBasicService.getUserBasicById(id);
        List<Topic> topicList = topicService.getTopicList(currentFriend);
        currentFriend.setTopicList(topicList);

        session.setAttribute("friend",currentFriend);
        return "index";
    }
}
