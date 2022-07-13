package com.zylai.fruit.service.impl;

import com.zylai.fruit.service.FruitService;
import com.zylai.fruit.dao.FruitDAO;
import com.zylai.fruit.pojo.Fruit;

import java.util.List;

/**
 * @Author: Zhao YunLai
 * @Date: 2022/07/09/17:48
 * @Description:
 */
public class FruitServiceImpl implements FruitService {

    private FruitDAO fruitDAO = null;

    @Override
    public List<Fruit> getFruitList(String keyword, Integer pageNo) {
        return fruitDAO.getFruitPageList(keyword, pageNo);
    }

    @Override
    public void addFruit(Fruit fruit) {
        fruitDAO.addFruit(fruit);
    }

    @Override
    public Fruit getFruitById(Integer fid) {
        return fruitDAO.getFruitByFid(fid);
    }

    @Override
    public void delFruit(Integer fid) {
        fruitDAO.deleteFruitByFid(fid);
    }

    @Override
    public Integer getPageCount(String keyword) {
        long count = fruitDAO.getFruitCount(keyword);
        return (int)(count+5-1)/5;
    }

    @Override
    public void updateFruit(Fruit fruit) {
        fruitDAO.updateFruit(fruit);
    }
}
