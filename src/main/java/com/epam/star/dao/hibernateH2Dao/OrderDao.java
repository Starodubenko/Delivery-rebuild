package com.epam.star.dao.hibernateH2Dao;

import com.epam.star.entity.Client;
import com.epam.star.entity.Goods;
import com.epam.star.entity.Order2;

import java.util.List;

public interface OrderDao extends GenericDao<Order2, Integer> {

    Order2 findByNumber(String number, boolean isCart);

    List<Order2> findByClient(Client client, boolean isCart);

    List<Order2> findByGoods(Goods goods, boolean isCart);
}


