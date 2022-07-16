package com.zylai.qqzone.dao.impl;

import com.zylai.myssm.basedao.BaseDAO;
import com.zylai.qqzone.dao.TopicDAO;
import com.zylai.qqzone.pojo.Topic;
import com.zylai.qqzone.pojo.UserBasic;

import java.util.List;

/**
 * @Author: Zhao YunLai
 * @Date: 2022/07/15/14:59
 * @Description:
 */
public class TopicDAOImpl extends BaseDAO<Topic> implements TopicDAO {

    @Override
    public List<Topic> getTopicList(UserBasic userBasic) {
        return super.executeQuery("select * from t_topic where author = ?",userBasic.getId());
    }

    @Override
    public Integer addTopic(Topic topic) {
        return super.insert("insert into t_topic(title,content,topicDate,author) values(?,?,?,?)",
                topic.getTitle(), topic.getContent(), topic.getTopicDate(), topic.getAuthor().getId());
    }

    @Override
    public void delTopic(Integer id) {
        super.executeUpdate("delete from t_topic where id = ?",id);
    }

    @Override
    public Topic getTopic(Integer id) {
        return super.load("select * from t_topic where id=?",id);
    }
}
