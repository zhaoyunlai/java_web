package com.zylai.jdbc01;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: Zhao YunLai
 * @Date: 2022/06/21/13:16
 * @Description: 查询所有库存
 */
public class Demo06SelectOne {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {

        Class.forName("com.mysql.jdbc.Driver");
        String url = "jdbc:mysql://localhost:3306/fruitdb?useSSL=false&useUnicode=true&characterEncoding=utf8";
        Connection connection = DriverManager.getConnection(url, "root", "root");

//        编写SQL语句
        String sql = "select * from t_fruit where fid = ?";
//        创建预处理命令对象
        PreparedStatement psmt = connection.prepareStatement(sql);
//        填充参数
        psmt.setInt(1,2);
//        由于fid是主键，所以可以不使用列表，这里改一下
//        执行查询，返回结果集
        ResultSet res = psmt.executeQuery();
//        判断下一行是否有数据，并且指针指到下一行
        if(res.next()){
//            表示当前行第一列的数据，因为这一列是int类型的数据，所以使用getInt
            int fid = res.getInt(1);
//            也可以通过结果集的列名
            String fname = res.getString("fname");
            int price = res.getInt(3);
            int fcount = res.getInt("fcount");
            String remark = res.getString("remark");
            Fruit fruit = new Fruit(fid,fname,price,fcount,remark);
            System.out.println(fruit);
        }
//        释放资源
        res.close();
        psmt.close();
        connection.close();
    }
}
