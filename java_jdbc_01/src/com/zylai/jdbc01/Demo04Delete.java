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
public class Demo04Delete {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {

        Class.forName("com.mysql.jdbc.Driver");
        String url = "jdbc:mysql://localhost:3306/fruitdb?useSSL=false&useUnicode=true&characterEncoding=utf8";
        Connection connection = DriverManager.getConnection(url, "root", "root");

        String sql = "delete from t_fruit where fid = ?";
        PreparedStatement psmt = connection.prepareStatement(sql);
        psmt.setInt(1,33);

        int i = psmt.executeUpdate();
        System.out.println(i>0?"删除成功":"删除失败");

        psmt.close();
        connection.close();

    }
}
