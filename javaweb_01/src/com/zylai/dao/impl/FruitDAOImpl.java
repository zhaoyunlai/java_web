package com.zylai.dao.impl;

import com.zylai.dao.FruitDAO;
import com.zylai.dao.base.BaseDAO;
import com.zylai.pojo.Fruit;

import java.util.List;

/**
 * @Author: Zhao YunLai
 * @Date: 2022/06/22/22:12
 * @Description:
 */
public class FruitDAOImpl extends BaseDAO<Fruit> implements FruitDAO {


    //    查询所有的库存信息
    @Override
    public List<Fruit> getFruitList() {
        return super.executeQuery("select * from t_fruit");
    }

    //    通过水果名称查询记录
    @Override
    public Fruit getFruitByName(String fname) {
        return super.load("select * from t_fruit where fname=?",fname);
    }

    //    添加水果
    @Override
    public boolean addFruit(Fruit fruit) {
        String sql = "insert into t_fruit values(0,?,?,?,?)";
        int i = super.executeUpdate(sql, fruit.getFname(), fruit.getPrice(), fruit.getFcount(), fruit.getRemark());
//        System.out.println(i);
        return i>0;
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
