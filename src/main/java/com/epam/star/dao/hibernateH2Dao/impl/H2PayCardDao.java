package com.epam.star.dao.hibernateH2Dao.impl;

import com.epam.star.dao.hibernateH2Dao.PayCardDao;
import com.epam.star.entity.PayCard;
import com.epam.star.entity.StatusPayCard;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import java.math.BigDecimal;
import java.util.List;

public class H2PayCardDao extends GenericJpaDao<PayCard, Integer> implements PayCardDao {
    private static final Logger LOGGER = LoggerFactory.getLogger(H2OrderedGoodsDao.class);


    public H2PayCardDao(EntityManager entityManager, Class<PayCard> persistentClass) {
        super(entityManager, persistentClass);
    }

    @Override
    public PayCard findBySerialNumber(String serialNumber) {
        Assert.notNull(serialNumber);

        PayCard payCard = null;

        Query query = getEntityManager().createQuery("select e from "
                + getPersistentClass().getSimpleName()
                + " e where serialNumber = :serialNumber")
                .setParameter("serialNumber", serialNumber);

        try{
            payCard = (PayCard) query.getSingleResult();
        }catch (NoResultException e){}

        return payCard;
    }

    @Override
    public PayCard findBySecretNumber(String secretNumber) {
        Assert.notNull(secretNumber);

        PayCard payCard = null;

        Query query = getEntityManager().createQuery("select e from "
                + getPersistentClass().getSimpleName()
                + " e where secretNumber = :secretNumber")
                .setParameter("secretNumber", secretNumber);

        try{
            payCard = (PayCard) query.getSingleResult();
        }catch (NoResultException e){}

        return payCard;
    }

    @Override
    public List<PayCard> findByBalance(Integer balance) {
        Assert.notNull(balance);

        List<PayCard> payCard = null;

        Query query = getEntityManager().createQuery("select e from "
                + getPersistentClass().getSimpleName()
                + " e where balance = :balance")
                .setParameter("balance", new BigDecimal(balance));

        try{
            payCard = query.getResultList();
        }catch (NoResultException e){}

        return payCard;
    }

    @Override
    public List<PayCard> findByStatus(StatusPayCard status) {
        Assert.notNull(status);

        List<PayCard> payCard = null;

        Query query = getEntityManager().createQuery("select e from "
                + getPersistentClass().getSimpleName()
                + " e where statusPayCard = :status")
                .setParameter("status", status);

        try{
            payCard = query.getResultList();
        }catch (NoResultException e){}

        return payCard;
    }
}
