package com.zylai.qqzone.service;

import com.zylai.qqzone.pojo.HostReply;

/**
 * @Author: Zhao YunLai
 * @Date: 2022/07/16/10:09
 * @Description:
 */
public interface HostReplyService {
    HostReply getHostReplyByReplyId(Integer replyId);
    void delHostReply(Integer id);

    void addHostReply(HostReply hostReply);
}
