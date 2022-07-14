package com.zylai.myssm.trans;

import com.zylai.myssm.basedao.ConnUtil;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * @Author: Zhao YunLai
 * @Date: 2022/07/14/9:28
 * @Description: 完成事务操作的一个类
 */
public class TransactionManager {

    //开启事务
    public static void beginTrans() throws SQLException {
        ConnUtil.getConn().setAutoCommit(false);
    }

    //提交事务
    public static void commit() throws SQLException {
        Connection conn = ConnUtil.getConn();
        conn.commit();
        //提交之后关闭conn
        ConnUtil.closeConn();
    }

    //回滚事务
    public static void rollback() throws SQLException {
        Connection conn = ConnUtil.getConn();
        conn.rollback();
        //回滚之后关闭conn
        ConnUtil.closeConn();
    }
}
