package com.zylai.jdbc01;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @Author: Zhao YunLai
 * @Date: 2022/06/20/22:58
 * @Description: 目标：和数据库建立连接
 *
 * JDBC：sun发布的一个java程序和数据库之间通信的规范（接口）
 *
 * 各大数据库厂商去实现JDBC规范（实现类），这些实现类打成压缩包，就是所谓的jar包
 *
 */
public class Demo01 {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
//        1.添加jar包
//        2.加载驱动
       Class.forName("com.mysql.jdbc.Driver");
//       3.通过驱动管理器获取连接对象
//        3-1 准备url
        String url = "jdbc:mysql://localhost:3306/fruitdb";
//        3-2 准备用户名
        String usr = "root";
//        3-3 准备密码
        String pwd = "root";
        Connection connection = DriverManager.getConnection(url,usr,pwd);
        System.out.println("conn = "+connection);
    }
}
