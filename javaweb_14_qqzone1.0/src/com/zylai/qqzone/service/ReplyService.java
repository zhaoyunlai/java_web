package com.zylai.qqzone.service;

import com.zylai.qqzone.pojo.Reply;

import java.util.List;

/**
 * @Author: Zhao YunLai
 * @Date: 2022/07/16/9:56
 * @Description:
 */
public interface ReplyService {
    //根据topic的id获取关联的所有回复
    List<Reply> getReplyListByTopicId(Integer topicId);

    void addReply(Reply reply);

    void deleteReplyById(Integer id);
    //删除指定的日志关联的所有的回复
    void deleteReplyByTopic(Integer topicId);
}
