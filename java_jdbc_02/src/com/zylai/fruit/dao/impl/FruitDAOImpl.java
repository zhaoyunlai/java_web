package com.zylai.fruit.dao.impl;

import com.zylai.fruit.dao.FruitDAO;
import com.zylai.fruit.pojo.Fruit;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: Zhao YunLai
 * @Date: 2022/06/21/18:05
 * @Description:
 */
public class FruitDAOImpl implements FruitDAO {
    private final String DRIVER = "com.mysql.jdbc.Driver";
    private final String URL = "jdbc:mysql://localhost:3306/fruitdb?useUnicode=true&characterEncoding=utf-8&useSSL=false";
    private final String USER = "root";
    private final String PWD = "root";

    private Connection getConnection(){
        try {
            //       1.加载驱动
            Class.forName(DRIVER);
            //            2.通过驱动获取连接对象
            return DriverManager.getConnection(URL, USER, PWD);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    private void close(Connection connection,PreparedStatement psmt, ResultSet rs){
        try {
            if(rs!=null){
                rs.close();
            }
            if(psmt!=null){
                psmt.close();
            }
            if(connection!=null){
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

//    查询所有的库存信息
    @Override
    public List<Fruit> getFruitList() {
        List<Fruit> fruitList = new ArrayList<>();
        Connection connection = null;
        PreparedStatement psmt = null;
        ResultSet rs = null;
        try {
            connection = getConnection();
//            3.编写SQL语句
            String sql = "select * from t_fruit";
//            4.创建预处理命令对象
             psmt = connection.prepareStatement(sql);
//            5.执行查询
             rs = psmt.executeQuery();
//            6.解析查询
            while(rs.next()){
                int fid = rs.getInt(1);
                String fname = rs.getString(2);
                int price = rs.getInt(3);
                int fcount = rs.getInt(4);
                String remark = rs.getString(5);

                Fruit fruit = new Fruit(fid,fname,price,fcount,remark);
                fruitList.add(fruit);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
//            关闭资源
            close(connection,psmt,rs);
        }
        return fruitList;
    }

//    通过水果名称查询记录
    @Override
    public Fruit getFruitByName(String fname) {
        Connection connection = null;
        PreparedStatement psmt = null;
        ResultSet rs = null;
        try {

            connection = getConnection();

            String sql = "select * from t_fruit where fname=?";
            psmt = connection.prepareStatement(sql);
            psmt.setString(1,fname);
            rs= psmt.executeQuery();
            if(rs.next()){
                int fid = rs.getInt("fid");
                int price = rs.getInt("price");
                int fcount = rs.getInt("fcount");
                String remark = rs.getString("remark");

                return new Fruit(fid,fname,price,fcount,remark);
            }

        } catch ( SQLException e) {
            e.printStackTrace();
        }finally {
            close(connection,psmt,rs);
        }
        return null;
    }

//    添加水果
    @Override
    public boolean addFruit(Fruit fruit) {
        Connection connection = null;
        PreparedStatement psmt = null;
        try {
            connection = getConnection();
            String sql = "insert into t_fruit values(0,?,?,?,?)";
            psmt = connection.prepareStatement(sql);
            psmt.setString(1,fruit.getFname());
            psmt.setInt(2,fruit.getPrice());
            psmt.setInt(3,fruit.getFcount());
            psmt.setString(4,fruit.getRemark());

            int i = psmt.executeUpdate();
            return i>0;
        } catch ( SQLException e) {
            e.printStackTrace();
        }finally {
            close(connection,psmt,null);
        }
        return false;
    }

//    更新水果库存信息
    @Override
    public boolean updateFruit(Fruit fruit) {
        Connection connection = null;
        PreparedStatement psmt = null;
        try {

            connection = getConnection();

            String sql = "update t_fruit set fname=?,price=?,fcount=?,remark=? where fid=?";
            psmt = connection.prepareStatement(sql);
            psmt.setString(1, fruit.getFname());
            psmt.setInt(2,fruit.getPrice());
            psmt.setInt(3,fruit.getFcount());
            psmt.setString(4,fruit.getRemark());
            psmt.setInt(5,fruit.getFid());

            int i = psmt.executeUpdate();
            return i>0;
        } catch ( SQLException e) {
            e.printStackTrace();
        }finally {
            close(connection,psmt,null);
        }
        return false;
    }

//    删除水果
    @Override
    public boolean deleteFruitByName(String fname) {
        Connection connection = null;
        PreparedStatement psmt = null;

        try {
            connection = getConnection();
            String sql = "delete from t_fruit where fname=?";
            psmt = connection.prepareStatement(sql);
            psmt.setString(1,fname);
            return psmt.executeUpdate()>0;
        } catch ( SQLException e) {
            e.printStackTrace();
        }finally {
            close(connection,psmt,null);
        }
        return false;
    }
}
