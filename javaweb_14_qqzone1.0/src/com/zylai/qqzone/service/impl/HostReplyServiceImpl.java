package com.zylai.qqzone.service.impl;

import com.zylai.qqzone.dao.HostReplyDAO;
import com.zylai.qqzone.pojo.HostReply;
import com.zylai.qqzone.service.HostReplyService;

/**
 * @Author: Zhao YunLai
 * @Date: 2022/07/16/10:10
 * @Description:
 */
public class HostReplyServiceImpl implements HostReplyService {
    private HostReplyDAO hostReplyDAO;
    @Override
    public HostReply getHostReplyByReplyId(Integer replyId) {
        return hostReplyDAO.getHostReplyByReplyId(replyId);
    }

    public void delHostReply(Integer id) {
        hostReplyDAO.delHostReply(id);
    }

    @Override
    public void addHostReply(HostReply hostReply) {
        hostReplyDAO.addHostReply(hostReply);
    }
}
