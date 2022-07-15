package com.zylai.qqzone.service;

import com.zylai.qqzone.pojo.Topic;
import com.zylai.qqzone.pojo.UserBasic;

import java.util.List;

/**
 * @Author: Zhao YunLai
 * @Date: 2022/07/15/15:10
 * @Description:
 */
public interface TopicService {
    //查询特定用户的日志列表
    List<Topic> getTopicList(UserBasic userBasic);
}
