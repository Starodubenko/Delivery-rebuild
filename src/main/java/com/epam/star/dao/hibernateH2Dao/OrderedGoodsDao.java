package com.epam.star.dao.hibernateH2Dao;

import com.epam.star.entity.Goods;
import com.epam.star.entity.OrderedGoods;

import java.util.List;

public interface OrderedGoodsDao extends GenericDao<OrderedGoods, Integer> {

    OrderedGoods findByGoods(Goods goods);

    List<OrderedGoods> findRange(int first, int count);
}
