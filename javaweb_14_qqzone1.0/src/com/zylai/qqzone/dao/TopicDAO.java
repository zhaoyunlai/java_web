package com.zylai.qqzone.dao;

import com.zylai.qqzone.pojo.Topic;
import com.zylai.qqzone.pojo.UserBasic;

import java.util.List;

/**
 * @Author: Zhao YunLai
 * @Date: 2022/07/15/14:31
 * @Description:
 */
public interface TopicDAO {
    //获取指定用户的所有日志
    List<Topic> getTopicList(UserBasic userBasic);
    //添加日志
    void addTopic(Topic topic);
    //删除日志
    void delTopic(Integer id);
    //获取特定日志信息
    Topic getTopic(Integer id);
}
