package com.zylai.fruit.dao.impl;

import com.zylai.fruit.dao.FruitDAO;
import com.zylai.fruit.pojo.Fruit;
import com.zylai.myssm.basedao.BaseDAO;

import java.util.List;

/**
 * @Author: Zhao YunLai
 * @Date: 2022/06/25/17:49
 * @Description:
 */
public class FruitDAOImpl extends BaseDAO<Fruit> implements FruitDAO {

    @Override
    public List<Fruit> getFruitPageList(String keyword,Integer pageNo) {
        return super.executeQuery("select * from t_fruit where fname like ? or remark like ? limit ?,5",
                "%"+keyword+"%","%"+keyword+"%",(pageNo-1)*5);
    }

    @Override
    public List<Fruit> getFruitList() {
        return super.executeQuery("select * from t_fruit");
    }

    @Override
    public Fruit getFruitByFid(int fid) {
        return super.load("select * from t_fruit where fid = ?",fid);
    }

    @Override
    public boolean updateFruit(Fruit fruit) {
        int i = super.executeUpdate("pdate t_fruit set fname=?,price=?,fcount=?,remark=? where fid=?",
                fruit.getFname(), fruit.getPrice(), fruit.getFcount(), fruit.getRemark(), fruit.getFid());

        return i>0;
    }

    @Override
    public void deleteFruitByFid(int fid) {
        super.executeUpdate("delete from t_fruit where fid=?",fid);
    }

    @Override
    public void addFruit(Fruit fruit) {
        super.insert("insert into t_fruit values(0,?,?,?,?)",
                fruit.getFname(),fruit.getPrice(),fruit.getFcount(),fruit.getRemark());

    }

    @Override
    public long getFruitCount(String keyword) {
        Object[] objects = super.executeComplexQuery("select count(*) from t_fruit where fname like ? or remark like ?",
                "%"+keyword+"%","%"+keyword+"%");
        return (Long)objects[0];
    }
}
