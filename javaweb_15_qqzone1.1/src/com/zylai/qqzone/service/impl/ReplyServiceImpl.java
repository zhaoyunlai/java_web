package com.zylai.qqzone.service.impl;

import com.zylai.qqzone.dao.ReplyDAO;
import com.zylai.qqzone.pojo.HostReply;
import com.zylai.qqzone.pojo.Reply;
import com.zylai.qqzone.pojo.UserBasic;
import com.zylai.qqzone.service.ReplyService;
import com.zylai.qqzone.service.UserBasicService;

import java.util.List;

/**
 * @Author: Zhao YunLai
 * @Date: 2022/07/16/9:57
 * @Description:
 */
public class ReplyServiceImpl implements ReplyService {

    private ReplyDAO replyDAO;
    //此处引入的是其他POJO对应的service接口，而不是DAO就扣
    //其他POJO对应的业务逻辑是封装在service层中的，我需要调用别人的业务逻辑方法，
    //而不需要去深入考虑人家内部的细节
    private HostReplyServiceImpl hostReplyService;
    private UserBasicService userBasicService;

    @Override
    public List<Reply> getReplyListByTopicId(Integer topicId) {
        List<Reply> replyList = replyDAO.getReplyList(topicId);

        //去查询每个回复对应的主人回复
        for (Reply reply : replyList) {
            //获取关联的作者
            UserBasic author = userBasicService.getUserBasicById(reply.getAuthor().getId());
            reply.setAuthor(author);

            //查询关联的hostReply
            HostReply hostReply = hostReplyService.getHostReplyByReplyId(reply.getId());
            reply.setHostReply(hostReply);
        }

        return replyList;
    }

    @Override
    public void addReply(Reply reply) {
        replyDAO.addReply(reply);
    }

    @Override
    public void deleteReplyById(Integer id) {
        //1.根据id获取到reply
        Reply reply = replyDAO.getReplyById(id);

        //2.如果reply有关联的hostReply，则先删除hostReply
        if(reply!=null){
            //查询关联的hostReply
            HostReply hostReply = hostReplyService.getHostReplyByReplyId(reply.getId());
            if(hostReply!=null){
                hostReplyService.delHostReply(hostReply.getId());
            }
        }
        //3.删除reply
        replyDAO.delReply(id);
    }

    @Override
    public void deleteReplyByTopic(Integer topicId) {
        List<Reply> replyList = replyDAO.getReplyList(topicId);
        if(replyList!=null){
            for (Reply reply : replyList) {
                deleteReplyById(reply.getId());
            }
        }
    }
}
