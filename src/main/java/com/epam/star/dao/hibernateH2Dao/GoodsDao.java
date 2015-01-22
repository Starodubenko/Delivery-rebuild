package com.epam.star.dao.hibernateH2Dao;

import com.epam.star.entity.Goods;

import java.util.List;

public interface GoodsDao extends GenericDao<Goods, Integer> {

    Goods findByName(String name);

    List<Goods> findRange(int first, int count);
}
