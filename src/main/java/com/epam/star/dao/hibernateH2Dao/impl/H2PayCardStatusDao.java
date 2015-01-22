package com.epam.star.dao.hibernateH2Dao.impl;

import com.epam.star.dao.hibernateH2Dao.PayCartStatusDao;
import com.epam.star.entity.StatusPayCard;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;

public class H2PayCardStatusDao extends GenericJpaDao<StatusPayCard, Integer> implements PayCartStatusDao {
    private static final Logger LOGGER = LoggerFactory.getLogger(H2OrderedGoodsDao.class);


    public H2PayCardStatusDao(EntityManager entityManager, Class<StatusPayCard> persistentClass) {
        super(entityManager, persistentClass);
    }

    @Override
    public StatusPayCard findByStatusName(String name) {
        Assert.notNull(name);

        StatusPayCard statusPayCard = null;

        Query query = getEntityManager().createQuery("select e from "
                + getPersistentClass().getSimpleName()
                + " e where lower(statusName)  = lower(:statusName)")
                .setParameter("statusName", name);

        try{
            statusPayCard = (StatusPayCard) query.getSingleResult();
        }catch (NoResultException e){}

        return statusPayCard;
    }
}
