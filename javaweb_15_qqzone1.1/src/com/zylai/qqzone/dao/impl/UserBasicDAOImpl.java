package com.zylai.qqzone.dao.impl;

import com.zylai.myssm.basedao.BaseDAO;
import com.zylai.qqzone.dao.UserBasicDAO;
import com.zylai.qqzone.pojo.UserBasic;

import java.util.List;

/**
 * @Author: Zhao YunLai
 * @Date: 2022/07/15/14:35
 * @Description:
 */
public class UserBasicDAOImpl extends BaseDAO<UserBasic> implements UserBasicDAO {
    @Override
    public UserBasic getUserBasic(String loginId, String pwd) {
        return super.load("select * from t_user_basic where loginId = ? and pwd = ?",loginId,pwd);
    }

    @Override
    public List<UserBasic> getUserBasicList(UserBasic userBasic) {
//        String sql ="SELECT\n" +
//                "        t3.id,\n" +
//                "                t3.loginId,\n" +
//                "                t3.nickName,\n" +
//                "                t3.headImg\n" +
//                "        FROM\n" +
//                "        t_user_basic t1\n" +
//                "        LEFT OUTER JOIN t_friend t2 ON t1.id = t2.uid\n" +
//                "        LEFT OUTER JOIN t_user_basic t3 ON t2.fid = t3.id\n" +
//                "        WHERE\n" +
//                "        t2.uid = 1";
        String sql = "select fid as 'id' from t_friend where uid = ?";
        return super.executeQuery(sql,userBasic.getId());
    }

    @Override
    public UserBasic getUserBasicById(Integer id) {
        return super.load("select * from t_user_basic where id = ?",id);
    }
}
