package com.zylai.qqzone.controller;

import com.zylai.qqzone.pojo.Reply;
import com.zylai.qqzone.pojo.Topic;
import com.zylai.qqzone.pojo.UserBasic;
import com.zylai.qqzone.service.ReplyService;

import javax.servlet.http.HttpSession;
import java.util.Date;

/**
 * @Author: Zhao YunLai
 * @Date: 2022/07/16/12:08
 * @Description:
 */
public class ReplyController {

    private ReplyService replyService;

    public String addReply(String content, Integer topicId,HttpSession session){
        UserBasic author = (UserBasic) session.getAttribute("userBasic");
        Reply reply = new Reply(content,new Date(),author,new Topic(topicId));
        replyService.addReply(reply);
        return "redirect:topic.do?operate=topicDetail&id="+topicId;
    }

    public String delReply(Integer replyId,Integer topicId){
        replyService.deleteReplyById(replyId);
        return "redirect:topic.do?operate=topicDetail&id="+topicId;
    }
}
