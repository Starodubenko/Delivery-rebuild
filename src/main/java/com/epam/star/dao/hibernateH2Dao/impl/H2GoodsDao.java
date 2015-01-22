package com.epam.star.dao.hibernateH2Dao.impl;

import com.epam.star.dao.hibernateH2Dao.GoodsDao;
import com.epam.star.entity.Goods;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import java.util.List;

public class H2GoodsDao extends GenericJpaDao<Goods, Integer> implements GoodsDao {
    private static final Logger LOGGER = LoggerFactory.getLogger(H2GoodsDao.class);

    public H2GoodsDao(EntityManager entityManager, Class<Goods> persistentClass) {
        super(entityManager, persistentClass);
    }


    @Override
    public Goods findByName(String name) {
        Assert.notNull(name);

        Goods goods = null;

        Query query = getEntityManager().createQuery("select e from "
                + getPersistentClass().getSimpleName()
                + " e where goodsName = :name")
                .setParameter("name", name);

        try{
            goods = (Goods) query.getSingleResult();
        }catch (NoResultException e){}

        return goods;
    }

    @Override
    public List<Goods> findRange(int first, int count) {

        List<Goods> goods = null;

        Query query = getEntityManager().createQuery("select e from "
                + getPersistentClass().getSimpleName()
                + " e order by goodsName asc")
                .setFirstResult(first)
                .setMaxResults(count);

        try{
            goods = query.getResultList();
        }catch (NoResultException e){}

        return goods;
    }
}
