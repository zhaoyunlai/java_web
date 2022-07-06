package com.zylai.jdbc01;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: Zhao YunLai
 * @Date: 2022/06/21/13:16
 * @Description: 查询所有库存
 */
public class Demo05SelectAll {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {

//        1.加载驱动
        Class.forName("com.mysql.jdbc.Driver");
        String url = "jdbc:mysql://localhost:3306/fruitdb?useSSL=false&useUnicode=true&characterEncoding=utf8";
//        2.获取连接对象
        Connection connection = DriverManager.getConnection(url, "root", "root");

//        3.编写SQL语句
        String sql = "select * from t_fruit";
//        4.创建预处理命令对象
        PreparedStatement psmt = connection.prepareStatement(sql);
//        5.填充参数，这里没有，略
//        6.执行查询，返回结果集
        ResultSet res = psmt.executeQuery();
        List<Fruit> list = new ArrayList<>();
//        7.处理结果集
//        判断下一行是否有数据，并且指针指到下一行
        while(res.next()){
//            表示当前行第一列的数据，因为这一列是int类型的数据，所以使用getInt
            int fid = res.getInt(1);
//            也可以通过结果集的列名
            String fname = res.getString("fname");
            int price = res.getInt(3);
            int fcount = res.getInt("fcount");
            String remark = res.getString("remark");

            Fruit fruit = new Fruit(fid,fname,price,fcount,remark);
            list.add(fruit);
        }
//        8.释放资源
        res.close();
        psmt.close();
        connection.close();
        for (Fruit fruit : list) {
            System.out.println(fruit);
        }
    }
}
