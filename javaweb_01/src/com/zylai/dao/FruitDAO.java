package com.zylai.dao;

import com.zylai.pojo.Fruit;

import java.util.List;

/**
 * @Author: Zhao YunLai
 * @Date: 2022/06/22/22:11
 * @Description:
 */
public interface FruitDAO {
    //    查询库存列表
    List<Fruit> getFruitList();

    //    根据水果名称查询库存
    Fruit getFruitByName(String fname);
    //    新增水果库存
    boolean addFruit(Fruit fruit);
    //    修改库存
    boolean updateFruit(Fruit fruit);
    //    根据水果名称删除记录
    boolean deleteFruitByName(String fname);

}
