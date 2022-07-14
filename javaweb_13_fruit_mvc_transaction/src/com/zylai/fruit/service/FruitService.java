package com.zylai.fruit.service;

import com.zylai.fruit.pojo.Fruit;

import java.util.List;

/**
 * @Author: Zhao YunLai
 * @Date: 2022/07/09/17:45
 * @Description:
 */
public interface FruitService {
    //获取指定页面的库存列表信息
    List<Fruit> getFruitList(String keyword,Integer pageNo);
    //添加库存记录
    void addFruit(Fruit fruit);
    //根据id查看指定库存记录
    Fruit getFruitById(Integer fid);
    //删除指定库存记录
    void delFruit(Integer fid);
    //获取总页数
    Integer getPageCount(String keyword);
    //修改特定库存记录
    void  updateFruit(Fruit fruit);
}
