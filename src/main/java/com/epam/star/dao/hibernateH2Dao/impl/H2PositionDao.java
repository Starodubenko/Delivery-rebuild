package com.epam.star.dao.hibernateH2Dao.impl;

import com.epam.star.dao.hibernateH2Dao.PositionDao;
import com.epam.star.entity.Position;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;

public class H2PositionDao extends GenericJpaDao<Position, Integer> implements PositionDao {
    private static final Logger LOGGER = LoggerFactory.getLogger(H2OrderedGoodsDao.class);

    public H2PositionDao(EntityManager entityManager, Class<Position> persistentClass) {
        super(entityManager, persistentClass);
    }


    @Override
    public Position findByPositionName(String positionName) {
        Assert.notNull(positionName);

        Position position = null;

        Query query = getEntityManager().createQuery("select e from "
                + getPersistentClass().getSimpleName()
                + " e where lower(positionName)  = lower(:positionName)")
                .setParameter("positionName", positionName);

        try{
            position = (Position) query.getSingleResult();
        }catch (NoResultException e){}

        return position;
    }
}
