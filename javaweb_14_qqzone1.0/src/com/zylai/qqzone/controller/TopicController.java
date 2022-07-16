package com.zylai.qqzone.controller;

import com.zylai.qqzone.pojo.Topic;
import com.zylai.qqzone.pojo.UserBasic;
import com.zylai.qqzone.service.TopicService;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;

/**
 * @Author: Zhao YunLai
 * @Date: 2022/07/16/9:50
 * @Description:
 */
public class TopicController {
    private TopicService topicService;

    public String topicDetail(Integer id, HttpSession session){
        Topic topic = topicService.getTopicById(id);

        session.setAttribute("topic",topic);
        return "frames/detail";
    }

    public String delTopic(Integer topicId){
        topicService.delTopic(topicId);
        return "redirect:topic.do?operate=getTopicList";
    }

    public String getTopicList(HttpSession session){
        //从session中获取当前用户信息
        UserBasic userBasic = (UserBasic) session.getAttribute("userBasic");
        //再次查询当前用户关联的所有日志
        List<Topic> topicList = topicService.getTopicList(userBasic);
        //设置一下关联的日志列表
        userBasic.setTopicList(topicList);
        //重新覆盖一下friend中的信息，为啥不覆盖userBasic呢？因为main.html中迭代的是friend的
        session.setAttribute("friend",userBasic);
        return "frames/main";
    }

    public String addTopic(String title,String content,Integer authorId,HttpSession session){
        //添加topic
        Topic topic = new Topic(title, content, new Date(), new UserBasic(authorId));
        //这里需要获取添加完成之后主键的值，为了获取更加完备的topic对象
        Integer key = topicService.addTopic(topic);
        topic.setId(key);
        //从session中获取当前用户信息，更新当前用户的日志列表并覆盖
        //其实也可以直接重新再查询一遍用户的日志，但是我觉得莫得必要了这里
        UserBasic userBasic = (UserBasic) session.getAttribute("userBasic");
        userBasic.getTopicList().add(topic);
        //重新覆盖一下friend中的信息，为啥不覆盖userBasic呢？因为main.html中迭代的是friend的
        session.setAttribute("friend",userBasic);
        return "index";
    }
}
