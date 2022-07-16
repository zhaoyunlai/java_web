package com.zylai.qqzone.dao.impl;

import com.zylai.myssm.basedao.BaseDAO;
import com.zylai.qqzone.dao.ReplyDAO;
import com.zylai.qqzone.pojo.Reply;

import java.util.List;

/**
 * @Author: Zhao YunLai
 * @Date: 2022/07/16/9:59
 * @Description:
 */
public class ReplyDAOImpl  extends BaseDAO<Reply> implements ReplyDAO {
    @Override
    public List<Reply> getReplyList(Integer topicId) {
        return super.executeQuery("select * from t_reply where topic = ?",topicId);
    }

    @Override
    public void addReply(Reply reply) {
         super.executeUpdate("insert into t_reply(id,content,replyDate,author,topic) values(0,?,?,?,?)",
                reply.getContent(),reply.getReplyDate(),reply.getAuthor().getId(),reply.getTopic().getId());

    }

    @Override
    public void delReply(Integer id) {
        executeUpdate("delete from t_reply where id = ?",id);
    }

    @Override
    public Reply getReplyById(Integer id) {
        return super.load("select * from t_reply where id = ?",id);
    }
}
