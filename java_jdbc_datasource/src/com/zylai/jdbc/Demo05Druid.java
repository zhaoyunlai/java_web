package com.zylai.jdbc;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;


/**
 * @Author: Zhao YunLai
 * @Date: 2022/06/22/14:13
 * @Description: 直接通过配置文件创建连接池
 */
public class Demo05Druid {
    public static void main(String[] args) throws Exception {

        Properties properties = new Properties();
        InputStream is = Demo05Druid.class.getClassLoader().getResourceAsStream("jdbc2.properties");
        properties.load(is);

        DataSource dataSource = DruidDataSourceFactory.createDataSource(properties);

        for (int i = 0; i < 10; i++) {
            Connection conn1 = dataSource.getConnection();

            System.out.println(i+" "+conn1);

        }
    }

}
