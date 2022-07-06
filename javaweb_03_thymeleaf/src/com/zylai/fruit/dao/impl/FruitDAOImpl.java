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
    public List<Fruit> getFruitList() {
        return super.executeQuery("select * from t_fruit");
    }
}
