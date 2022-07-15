package com.zylai.qqzone.service.impl;

import com.zylai.qqzone.dao.TopicDAO;
import com.zylai.qqzone.pojo.Topic;
import com.zylai.qqzone.pojo.UserBasic;
import com.zylai.qqzone.service.TopicService;

import java.util.List;

/**
 * @Author: Zhao YunLai
 * @Date: 2022/07/15/15:12
 * @Description:
 */
public class TopicServiceImpl implements TopicService {

    private TopicDAO topicDAO;

    @Override
    public List<Topic> getTopicList(UserBasic userBasic) {
        return topicDAO.getTopicList(userBasic);
    }
}
