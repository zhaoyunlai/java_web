package com.zylai.jdbc01;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * @Author: Zhao YunLai
 * @Date: 2022/06/21/12:54
 * @Description: 添加
 */
public class Demo02 {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
//        1.加载驱动
        Class.forName("com.mysql.jdbc.Driver");
        //       2.通过驱动管理器获取连接对象
//        2-1 准备url
//        url表示和数据库通信的地址，如果需要带参数，则需要使用？进行连接
//        如果使用多个参数,多个参数之间使用&连接
        String url = "jdbc:mysql://localhost:3306/fruitdb?useSSL=false&useUnicode=true&characterEncoding=utf-8";
//        2-2 准备用户名
        String usr = "root";
//        2-3 准备密码
        String pwd = "root";

        Connection connection = DriverManager.getConnection(url,usr,pwd);

//        3. 编写SQL语句
//        id，fname,price,fcount,remark
        String sql = "insert into t_fruit value(0,?,?,?,?)";

//        4.创建预处理对象
        PreparedStatement psmt = connection.prepareStatement(sql);
//        5.填充参数
        psmt.setString(1,"榴莲");
        psmt.setInt(2,15);
        psmt.setInt(3,100);
        psmt.setString(4,"榴莲是一种神奇的水果");
//        6.执行更新（增删改），返回影响行数
        int i = psmt.executeUpdate();
        System.out.println(i>0?"添加成功！":"添加失败");
//        7.释放资源
        psmt.close();
        connection.close();
    }
}
