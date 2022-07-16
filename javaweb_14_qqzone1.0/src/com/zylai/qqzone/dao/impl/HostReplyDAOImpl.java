package com.zylai.qqzone.dao.impl;

import com.zylai.myssm.basedao.BaseDAO;
import com.zylai.qqzone.dao.HostReplyDAO;
import com.zylai.qqzone.pojo.HostReply;

/**
 * @Author: Zhao YunLai
 * @Date: 2022/07/16/10:08
 * @Description:
 */
public class HostReplyDAOImpl extends BaseDAO<HostReply> implements HostReplyDAO {
    @Override
    public HostReply getHostReplyByReplyId(Integer replyId) {
        return super.load("select * from t_host_reply where reply = ?",replyId);
    }

    @Override
    public void delHostReply(Integer id) {
        super.executeUpdate("delete from t_host_reply where id = ?",id);
    }

    @Override
    public void addHostReply(HostReply hostReply) {
        super.executeUpdate("insert into t_host_reply(content,hostReplyDate,author,reply) values(?,?,?,?)",
                hostReply.getContent(),hostReply.getHostReplyDate(),hostReply.getAuthor().getId(),hostReply.getReply().getId());
    }
}
