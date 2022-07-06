package com.zylai.jdbc;

import com.alibaba.druid.pool.DruidDataSource;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

/**
 * @Author: Zhao YunLai
 * @Date: 2022/06/22/14:13
 * @Description: 从配置文件中读取信息
 */
public class Demo04Druid {
    public static void main(String[] args) throws SQLException, IOException {

        Properties properties = new Properties();
        InputStream is = Demo04Druid.class.getClassLoader().getResourceAsStream("jdbc.properties");
        properties.load(is);
        DruidDataSource dataSource = new DruidDataSource();

        dataSource.setDriverClassName(properties.getProperty("jdbc.driverClassName"));
        dataSource.setUrl(properties.getProperty("jdbc.url"));
        dataSource.setUsername(properties.getProperty("jdbc.username"));
        dataSource.setPassword(properties.getProperty("jdbc.pwd"));

        dataSource.setInitialSize(Integer.parseInt(properties.getProperty("druid.initialSize")));
        dataSource.setMaxActive(Integer.parseInt(properties.getProperty("druid.maxActive")));
        dataSource.setMaxWait(Long.parseLong(properties.getProperty("druid.maxWait")));

        for (int i = 0; i < 10; i++) {
            Connection conn1 = dataSource.getConnection();
            System.out.println(i+" "+conn1);
        }
    }

}
