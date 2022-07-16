package com.zylai.qqzone.service.impl;

import com.zylai.qqzone.dao.TopicDAO;
import com.zylai.qqzone.pojo.Reply;
import com.zylai.qqzone.pojo.Topic;
import com.zylai.qqzone.pojo.UserBasic;
import com.zylai.qqzone.service.ReplyService;
import com.zylai.qqzone.service.TopicService;
import com.zylai.qqzone.service.UserBasicService;

import java.util.List;

/**
 * @Author: Zhao YunLai
 * @Date: 2022/07/15/15:12
 * @Description:
 */
public class TopicServiceImpl implements TopicService {

    private TopicDAO topicDAO;
    private ReplyService replyService;
    private UserBasicService userBasicService;

    @Override
    public List<Topic> getTopicList(UserBasic userBasic) {
        return topicDAO.getTopicList(userBasic);
    }

    @Override
    public Topic getTopicById(Integer id) {
        Topic topic = topicDAO.getTopic(id);

        //查询这个topic关联的作者信息
        UserBasic author = userBasicService.getUserBasicById(topic.getAuthor().getId());
        topic.setAuthor(author);

        //查询topic关联的回复，以及每个回复的主人回复
        List<Reply> replyList = replyService.getReplyListByTopicId(topic.getId());
        topic.setReplyList(replyList);

        return topic;
    }

    @Override
    public void delTopic(Integer id) {
        Topic topic = topicDAO.getTopic(id);
        if(topic!=null){
            //删除topic下关联的所有回复以及主人回复
            replyService.deleteReplyByTopic(id);
        }
        //删除topic
        topicDAO.delTopic(id);
    }

    @Override
    public Integer addTopic(Topic topic) {
        return topicDAO.addTopic(topic);
    }
}
