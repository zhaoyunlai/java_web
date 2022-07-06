package com.zylai.jdbc;

import com.alibaba.druid.pool.DruidDataSource;

import java.sql.Connection;
import java.sql.SQLException;


/**
 * @Author: Zhao YunLai
 * @Date: 2022/06/22/14:13
 * @Description: 验证连接池的部分参数
 */
public class Demo03Druid {
    public static void main(String[] args) throws SQLException {

        DruidDataSource dataSource = new DruidDataSource();

        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://localhost:3306/fruitdb?&useSSL=false&useUnicode=true&characterEncoding=utf-8");
        dataSource.setUsername("root");
        dataSource.setPassword("root");

        dataSource.setInitialSize(2);
        dataSource.setMaxActive(5);
        dataSource.setMaxWait(5000);


        for (int i = 0; i < 10; i++) {
            Connection conn1 = dataSource.getConnection();

            System.out.println(i+" "+conn1);

        }
    }

}
