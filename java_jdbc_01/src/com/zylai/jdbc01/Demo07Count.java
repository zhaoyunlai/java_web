package com.zylai.jdbc01;

import java.sql.*;

/**
 * @Author: Zhao YunLai
 * @Date: 2022/06/21/13:16
 * @Description: 查询所有库存
 */
public class Demo07Count {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {

        Class.forName("com.mysql.jdbc.Driver");
        String url = "jdbc:mysql://localhost:3306/fruitdb?useSSL=false&useUnicode=true&characterEncoding=utf8";
        Connection connection = DriverManager.getConnection(url, "root", "root");

//        编写SQL语句
        String sql = "select count(*) from t_fruit";
//        创建预处理命令对象
        PreparedStatement psmt = connection.prepareStatement(sql);
//        执行查询，返回结果集
//        这里返回的是一个一行一列的数据
        ResultSet res = psmt.executeQuery();
//        判断下一行是否有数据，并且指针指到下一行
        if(res.next()){
            int count = res.getInt(1);
            System.out.println("总记录条数："+count);
        }
//        释放资源
        res.close();
        psmt.close();
        connection.close();
    }
}
