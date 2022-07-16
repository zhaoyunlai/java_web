package com.zylai.qqzone.dao;

import com.zylai.qqzone.pojo.HostReply;

/**
 * @Author: Zhao YunLai
 * @Date: 2022/07/15/14:35
 * @Description:
 */
public interface HostReplyDAO {
    HostReply getHostReplyByReplyId(Integer replyId);
    void delHostReply(Integer id);
    void addHostReply(HostReply hostReply);
}
