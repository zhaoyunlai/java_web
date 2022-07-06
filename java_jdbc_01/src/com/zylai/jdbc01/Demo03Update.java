package com.zylai.jdbc01;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * @Author: Zhao YunLai
 * @Date: 2022/06/21/13:16
 * @Description:
 */
public class Demo03Update {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        Fruit fruit = new Fruit(33, "猕猴桃", "猕猴桃啊猕猴桃");

        Class.forName("com.mysql.jdbc.Driver");
        String url = "jdbc:mysql://localhost:3306/fruitdb?useSSL=false&useUnicode=true&characterEncoding=utf8";
        Connection connection = DriverManager.getConnection(url, "root", "root");

        String sql = "update t_fruit set fname=?,remark = ? where fid = ?";
        PreparedStatement psmt = connection.prepareStatement(sql);
        psmt.setString(1, fruit.getFname());
        psmt.setString(2, fruit.getRemark());
        psmt.setInt(3,fruit.getFid());
        int i = psmt.executeUpdate();
        System.out.println(i>0?"修改成功":"修改失败");

        psmt.close();
        connection.close();

    }
}
