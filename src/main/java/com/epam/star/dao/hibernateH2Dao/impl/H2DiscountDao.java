package com.epam.star.dao.hibernateH2Dao.impl;

import com.epam.star.dao.hibernateH2Dao.DiscountDao;
import com.epam.star.entity.Discount;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;

public class H2DiscountDao extends GenericJpaDao<Discount, Integer> implements DiscountDao {
    private static final Logger LOGGER = LoggerFactory.getLogger(H2DiscountDao.class);

    public H2DiscountDao(EntityManager entityManager, Class<Discount> persistentClass) {
        super(entityManager, persistentClass);
    }

    @Override
    public Discount findByName(String name) {
        Assert.notNull(name);

        Discount discount = null;

        Query query = getEntityManager().createQuery("select e from "
                + getPersistentClass().getSimpleName()
                + " e where name = :name")
                .setParameter("name", name);

        try{
            discount = (Discount) query.getSingleResult();
        }catch (NoResultException e){

        }

        return discount;
    }
}
