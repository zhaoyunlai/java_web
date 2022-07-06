package com.zylai.fruit.dao.base;

import java.sql.*;

/**
 * @Author: Zhao YunLai
 * @Date: 2022/06/21/21:45
 * @Description:
 */
public abstract class BaseDAO {
    protected final String DRIVER = "com.mysql.jdbc.Driver";
    protected final String URL = "jdbc:mysql://localhost:3306/fruitdb?useUnicode=true&characterEncoding=utf-8&useSSL=false";
    protected final String USER = "root";
    protected final String PWD = "root";

    protected Connection getConnection(){
        try {
            //       1.加载驱动
            Class.forName(DRIVER);
            //            2.通过驱动获取连接对象
            return DriverManager.getConnection(URL, USER, PWD);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

//    关闭资源
    protected  void close(Connection connection, PreparedStatement psmt, ResultSet rs){
        try {
            if(rs!=null){
                rs.close();
            }
            if(psmt!=null){
                psmt.close();
            }
            if(connection!=null){
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

//    执行更新，返回影响行数
    protected int executeUpdate(String sql,Object... params){
        Connection connection = null;
        PreparedStatement psmt = null;
        try {
            connection = getConnection();
            psmt = connection.prepareStatement(sql);
            if(params!=null && params.length>0){
                for (int i = 0; i < params.length; i++) {
                    psmt.setObject(i+1,params[i]);
                }
            }
            return psmt.executeUpdate();
        } catch ( SQLException e) {
            e.printStackTrace();
        }finally {
            close(connection,psmt,null);
        }
        return 0;
    }

}
