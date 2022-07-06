package com.zylai.fruit.dao;

import com.zylai.fruit.pojo.Fruit;

import java.util.List;

/**
 * @Author: Zhao YunLai
 * @Date: 2022/06/25/17:48
 * @Description:
 */
public interface FruitDAO {
    //获取指定页码上的库存列表信息，每页显示5条
    List<Fruit> getFruitPageList(String keyword,Integer pageNo);

    //    查询库存总记录条数
    long getFruitCount(String keyword);

//    获取所有库存列表信息
    List<Fruit> getFruitList();
//    根据fid获取水果库存信息
    Fruit getFruitByFid (int fid);
//    修改指定的库存记录
    boolean updateFruit(Fruit fruit);
//     根据指定的fid删除库存记录
    void deleteFruitByFid(int fid);
//    添加一条数据
    void addFruit(Fruit fruit);


}
