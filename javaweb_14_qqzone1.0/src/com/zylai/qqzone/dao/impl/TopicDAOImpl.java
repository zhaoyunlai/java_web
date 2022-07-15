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
    public void addTopic(Topic topic) {

    }

    @Override
    public void delTopic(Topic topic) {

    }

    @Override
    public Topic getTopic(Integer id) {
        return null;
    }
}
