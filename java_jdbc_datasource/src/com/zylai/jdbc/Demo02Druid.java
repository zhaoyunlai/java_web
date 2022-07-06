package com.zylai.jdbc;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidPooledConnection;

import java.sql.Connection;
import java.sql.SQLException;


/**
 * @Author: Zhao YunLai
 * @Date: 2022/06/22/14:13
 * @Description:
 */
public class Demo02Druid {
    public static void main(String[] args) throws SQLException {

        DruidDataSource dataSource = new DruidDataSource();

        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://localhost:3306/fruitdb?&useSSL=false&useUnicode=true&characterEncoding=utf-8");
        dataSource.setUsername("root");
        dataSource.setPassword("root");

//        证明两点：
//        1.被close的连接对象并没有真正关闭，而是将状态重新设置为空闲状态放回池子
//        2.没有被close的连接对象会被一直占用，那么下次继续获取连接对象，是不会获取到这个对象的
//        对于conn1对象指向别的东西，原始情况下jvm会回收conn1之前指向的对象，但是连接池中的连接默认有一个变量指着，所以不会被回收

        for (int i = 0; i < 5; i++) {
            Connection conn1 = dataSource.getConnection();
            Connection conn2 = dataSource.getConnection();

            System.out.println(conn1);
            System.out.println(conn2);

            if(i%3==0){
                conn1.close();
                conn2.close();
            }
        }
    }

}
