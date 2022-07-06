package com.zylai.jdbc01;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Arrays;

/**
 * @Author: Zhao YunLai
 * @Date: 2022/06/21/12:54
 * @Description: 添加
 */
public class DemoAddBatch {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
//        1.加载驱动
        Class.forName("com.mysql.jdbc.Driver");
        //       2.通过驱动管理器获取连接对象
//        批处理操作一
//        如果执行批处理任务，需要添加一个参数： rewriteBatchedStatements=true
        String url = "jdbc:mysql://localhost:3306/fruitdb?rewriteBatchedStatements=true&useSSL=false&useUnicode=true&characterEncoding=utf-8";
//        2-2 准备用户名
        String usr = "root";
//        2-3 准备密码
        String pwd = "root";
        Connection connection = DriverManager.getConnection(url,usr,pwd);
//        3. 编写SQL语句
        String sql = "insert into t_fruit value(0,?,?,?,?)";

//        4.创建预处理对象
        PreparedStatement psmt = connection.prepareStatement(sql);
//        5.填充参数
        for (int i = 0; i < 1000; i++) {
            psmt.setString(1,"榴莲"+i);
            psmt.setInt(2,15);
            psmt.setInt(3,100);
            psmt.setString(4,"榴莲是一种神奇的水果"+i);

//            批处理操作二：将数据加入一个批次
            psmt.addBatch();
//            每一百个处理一次，分批次处理，每次执行完要记得清空一下队列
            if(i%100==0){
                psmt.executeBatch();
//                记得clear一下
                psmt.clearBatch();
            }
        }
//        批处理操作三
        int[] batch = psmt.executeBatch();
        System.out.println(Arrays.toString(batch));

//        7.释放资源
        psmt.close();
        connection.close();
    }
}
