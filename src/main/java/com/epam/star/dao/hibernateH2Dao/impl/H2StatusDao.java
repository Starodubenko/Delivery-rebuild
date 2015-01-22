package com.epam.star.dao.hibernateH2Dao.impl;

import com.epam.star.dao.hibernateH2Dao.StatusDao;
import com.epam.star.entity.Status;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;

public class H2StatusDao extends GenericJpaDao<Status, Integer> implements StatusDao {
    private static final Logger LOGGER = LoggerFactory.getLogger(H2OrderedGoodsDao.class);

    public H2StatusDao(EntityManager entityManager, Class<Status> persistentClass) {
        super(entityManager, persistentClass);
    }


    @Override
    public Status findByStatusName(String statusName) {
        Assert.notNull(statusName);

        Status status = null;

        Query query = getEntityManager().createQuery("select e from "
                + getPersistentClass().getSimpleName()
                + " e where lower(statusName)  = lower(:statusName)")
                .setParameter("statusName", statusName);

        try{
            status = (Status) query.getSingleResult();
        }catch (NoResultException e){}

        return status;
    }
}
