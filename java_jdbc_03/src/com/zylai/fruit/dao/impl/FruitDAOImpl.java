package com.zylai.fruit.dao.impl;

import com.zylai.fruit.dao.FruitDAO;
import com.zylai.fruit.dao.base.BaseDAO;
import com.zylai.fruit.pojo.Fruit;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: Zhao YunLai
 * @Date: 2022/06/21/18:05
 * @Description:
 */
public class FruitDAOImpl extends BaseDAO implements FruitDAO {


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
        String sql = "insert into t_fruit values(0,?,?,?,?)";
        return super.executeUpdate(sql,fruit.getFname(),fruit.getPrice(),fruit.getFcount(),fruit.getRemark())>0;
    }

//    更新水果库存信息
    @Override
    public boolean updateFruit(Fruit fruit) {
        String sql = "update t_fruit set fname=?,price=?,fcount=?,remark=? where fid=?";
        return super.executeUpdate(sql,fruit.getFname(),fruit.getPrice(),fruit.getFcount(),fruit.getRemark(),fruit.getFid())>0;
    }

//    删除水果
    @Override
    public boolean deleteFruitByName(String fname) {
        String sql = "delete from t_fruit where fname=?";
        return super.executeUpdate(sql,fname)>0;
    }
}
