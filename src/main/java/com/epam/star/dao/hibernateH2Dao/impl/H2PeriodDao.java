package com.epam.star.dao.hibernateH2Dao.impl;

import com.epam.star.dao.hibernateH2Dao.PeriodDao;
import com.epam.star.entity.Period;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.EntityManager;

public class H2PeriodDao extends GenericJpaDao<Period, Integer> implements PeriodDao {
    private static final Logger LOGGER = LoggerFactory.getLogger(H2OrderedGoodsDao.class);

    public H2PeriodDao(EntityManager entityManager, Class<Period> persistentClass) {
        super(entityManager, persistentClass);
    }


}
