package com.epam.star.dao.hibernateH2Dao.impl;

import com.epam.star.dao.hibernateH2Dao.ClientDao;
import com.epam.star.entity.Client;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;


public class H2ClientDao extends GenericJpaDao<Client, Integer> implements ClientDao {
    private static final Logger LOGGER = LoggerFactory.getLogger(H2ClientDao.class);

    public H2ClientDao(EntityManager entityManager, Class<Client> persistentClass) {
        super(entityManager, persistentClass);
    }

    @Override
    public boolean checkAvailble(String login) {
        Assert.notNull(login);

        Query query = getEntityManager().createQuery("select count(*) from "
                + getPersistentClass().getSimpleName()
                + " e where lower(login) = lower(:login) ")
                .setParameter("login", login);

        Long count = (Long) query.getSingleResult();


        return count < 1;
    }

    @Override
    public Client findByCredentials(String login, String password) {
        Assert.notNull(login);

        Client client = null;

        Query query = getEntityManager().createQuery("select e from "
                + getPersistentClass().getSimpleName()
                + " e where lower(login) = lower(:login) and password = :password")
                .setParameter("login", login)
                .setParameter("password", password);

        try{
            client = (Client) query.getSingleResult();
        }catch (NoResultException e){

        }

        return client;
    }
}
