package com.zylai.myssm.basedao;

import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

/**
 * @Author: Zhao YunLai
 * @Date: 2022/07/14/9:50
 * @Description:
 */
public class ConnUtil {

    private static ThreadLocal<Connection> threadLocal = new ThreadLocal<>();

    protected static  String DRIVER;
    protected static  String URL;
    protected static  String USER;
    protected static  String PWD;

    private static DataSource dataSource;

    static{
        InputStream is = ConnUtil.class.getClassLoader().getResourceAsStream("jdbc.properties");
        Properties properties = new Properties();
        try {
            properties.load(is);
            dataSource = DruidDataSourceFactory.createDataSource(properties);
            //直接读取整个配置文件，不需要挨个属性读取了
//            DRIVER = properties.getProperty("jdbc.driver");
//            URL = properties.getProperty("jdbc.url");
//            USER = properties.getProperty("jdbc.user");
//            PWD = properties.getProperty("jdbc.pwd");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static Connection createConn(){
        //使用连接池
        try {
            return dataSource.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
//        try {
//            //       1.加载驱动
//            Class.forName(DRIVER);
//            //            2.通过驱动获取连接对象
//            return DriverManager.getConnection(URL, USER, PWD);
//        } catch (ClassNotFoundException | SQLException e) {
//            e.printStackTrace();
//        }
        return null;
    }

    public static Connection getConn(){
        Connection conn = threadLocal.get();
        if(conn==null){
            //如果为空，需要去创建一个connection对象
            conn = createConn();
            //将获取到的conn set到ThreadLocal中
            threadLocal.set(conn);
        }
        return threadLocal.get();
    }

    public static void closeConn() throws SQLException {
        Connection connection = threadLocal.get();
        if(connection==null){
            return;
        }
        if(!connection.isClosed()){
            connection.close();
            threadLocal.set(null);
        }
    }
}
