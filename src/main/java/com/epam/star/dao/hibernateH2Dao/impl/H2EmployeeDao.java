package com.epam.star.dao.hibernateH2Dao.impl;

import com.epam.star.entity.Employee;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.EntityManager;

public class H2EmployeeDao extends GenericJpaDao<Employee, Integer> {
    private static final Logger LOGGER = LoggerFactory.getLogger(H2EmployeeDao.class);


    public H2EmployeeDao(EntityManager entityManager, Class<Employee> persistentClass) {
        super(entityManager, persistentClass);
    }
}
