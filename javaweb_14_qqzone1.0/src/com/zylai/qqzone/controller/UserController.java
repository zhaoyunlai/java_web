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

        UserBasic userBasic = userBasicService.login(loginId, pwd);
        if(userBasic!=null){
            List<UserBasic> friendList = userBasicService.getFriendList(userBasic);
            List<Topic> topicList = topicService.getTopicList(userBasic);

            userBasic.setFriendList(friendList);
            userBasic.setTopicList(topicList);
            session.setAttribute("userBasic",userBasic);
            return "index";
        }
        return "login";
    }
}
