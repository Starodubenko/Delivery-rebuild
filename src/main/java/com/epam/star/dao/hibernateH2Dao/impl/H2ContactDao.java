package com.epam.star.dao.hibernateH2Dao.impl;

import com.epam.star.dao.hibernateH2Dao.ContactDao;
import com.epam.star.entity.Contact;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;

public class H2ContactDao extends GenericJpaDao<Contact, Integer> implements ContactDao {
    private static final Logger LOGGER = LoggerFactory.getLogger(H2ContactDao.class);


    public H2ContactDao(EntityManager entityManager, Class<Contact> persistentClass) {
        super(entityManager, persistentClass);
    }

    @Override
    public Contact findByTelephone(String telephone) {
        Assert.notNull(telephone);

        Contact contact = null;

        Query query = getEntityManager().createQuery("select e from "
                + getPersistentClass().getSimpleName()
                + " e where telephone = :telephone")
                .setParameter("telephone", telephone);

        try{
            contact = (Contact) query.getSingleResult();
        }catch (NoResultException e){

        }

        return contact;
    }

    @Override
    public Contact findByOwner(String owner) {
        Assert.notNull(owner);

        Contact contact = null;

        Query query = getEntityManager().createQuery("select e from "
                + getPersistentClass().getSimpleName()
                + " e where owner = :owner")
                .setParameter("owner", owner);

        try{
            contact = (Contact) query.getSingleResult();
        }catch (NoResultException e){

        }

        return contact;
    }
}
