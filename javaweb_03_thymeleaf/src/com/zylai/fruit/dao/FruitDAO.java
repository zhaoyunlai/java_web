package com.zylai.fruit.dao;

import com.zylai.fruit.pojo.Fruit;

import java.util.List;

/**
 * @Author: Zhao YunLai
 * @Date: 2022/06/25/17:48
 * @Description:
 */
public interface FruitDAO {
//    获取所有库存列表信息
    List<Fruit> getFruitList();
}
