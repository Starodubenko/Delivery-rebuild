package com.epam.star.dao.hibernateH2Dao.impl;

import com.epam.star.dao.hibernateH2Dao.OrderDao;
import com.epam.star.entity.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import java.util.List;

public class H2OrderDao extends GenericJpaDao<Order2, Integer> implements OrderDao {
    private static final Logger LOGGER = LoggerFactory.getLogger(H2OrderedGoodsDao.class);

    public H2OrderDao(EntityManager entityManager, Class<Order2> persistentClass) {
        super(entityManager, persistentClass);
    }

    @Override
    public Order2 findByNumber(String number, boolean isCart) {
        Assert.notNull(number);

        Order2 order = null;

        Query query = getEntityManager().createQuery("select e from "
                + getPersistentClass().getSimpleName()
                + " e where orderNumber = :orderNumber and cart = " + isCart)
                .setParameter("orderNumber", number);

        try{
            order = (Order2) query.getSingleResult();
        }catch (NoResultException e){}

        return order;
    }

    @Override
    public List<Order2> findByClient(Client client, boolean isCart) {
        Assert.notNull(client);

        List<Order2> orderList = null;

        Query query = getEntityManager().createQuery("select e from "
                + getPersistentClass().getSimpleName()
                + " e where user = :user and cart = "  + isCart)
                .setParameter("user", client);

        try{
            orderList = query.getResultList();
        }catch (NoResultException e){}

        return orderList;
    }

    @Override
    public List<Order2> findByGoods(Goods goods, boolean isCart) {
        Assert.notNull(goods);

        List<Order2> orderList = null;

        Query query = getEntityManager().createQuery("select e from "
                + getPersistentClass().getSimpleName()
                + " e where goods = :goods and cart = " + isCart)
                .setParameter("goods", goods);

        try{
            List<OrderedGoods> resultList = query.getResultList();

            for (OrderedGoods orderedGoods : resultList) {
                if (orderedGoods.getGoods().equals(goods)){
                    orderList.add(orderedGoods.getOrder());
                }
            }
        }catch (NoResultException e){}

        return orderList;
    }
}
