package com.epam.star.dao.hibernateH2Dao.impl;


import com.epam.star.dao.hibernateH2Dao.OrderedGoodsDao;
import com.epam.star.entity.Goods;
import com.epam.star.entity.OrderedGoods;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import java.util.List;

public class H2OrderedGoodsDao extends GenericJpaDao<OrderedGoods, Integer> implements OrderedGoodsDao {
    private static final Logger LOGGER = LoggerFactory.getLogger(H2OrderedGoodsDao.class);

    public H2OrderedGoodsDao(EntityManager entityManager, Class<OrderedGoods> persistentClass) {
        super(entityManager, persistentClass);
    }


    @Override
    public OrderedGoods findByGoods(Goods goods) {
        Assert.notNull(goods);

        OrderedGoods orderedGoods = null;

        Query query = getEntityManager().createQuery("select e from "
                + getPersistentClass().getSimpleName()
                + " e where goods = :goods")
                .setParameter("goods", goods);

        try{
            orderedGoods = (OrderedGoods) query.getSingleResult();
        }catch (NoResultException e){}

        return orderedGoods;
    }

    @Override
    public List<OrderedGoods> findRange(int first, int count) {

        List<OrderedGoods> orderedGoods = null;

        Query query = getEntityManager().createQuery("select e from "
                + getPersistentClass().getSimpleName() + " e")
                .setFirstResult(first)
                .setMaxResults(count);

        try{
            orderedGoods = query.getResultList();
        }catch (NoResultException e){}

        return orderedGoods;
    }
}
