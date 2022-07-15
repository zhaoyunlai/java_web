package com.zylai.qqzone.dao;

import com.zylai.qqzone.pojo.Reply;
import com.zylai.qqzone.pojo.Topic;

import java.util.List;

/**
 * @Author: Zhao YunLai
 * @Date: 2022/07/15/14:33
 * @Description:
 */
public interface ReplyDAO {
    //获取指定日志的回复列表
    List<Reply> getReplyList(Topic topic);
    //添加回复
    void addReply(Reply reply);
    //删除回复
    void delReply(Integer id);
}
