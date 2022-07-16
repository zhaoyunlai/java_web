package com.zylai.qqzone.controller;

import com.zylai.qqzone.pojo.HostReply;
import com.zylai.qqzone.pojo.Reply;
import com.zylai.qqzone.pojo.UserBasic;
import com.zylai.qqzone.service.HostReplyService;

import javax.servlet.http.HttpSession;
import java.util.Date;

/**
 * @Author: Zhao YunLai
 * @Date: 2022/07/16/19:46
 * @Description:
 */
public class HostReplyController {

    private HostReplyService hostReplyService;

    public String addHostReply(String content, Integer replyId, Integer topicId,HttpSession session){
        UserBasic author = (UserBasic) session.getAttribute("userBasic");
        HostReply hostReply = new HostReply(content,new Date(),author,new Reply(replyId));

        hostReplyService.addHostReply(hostReply);
        return "redirect:topic.do?operate=topicDetail&id="+topicId;
    }
}
