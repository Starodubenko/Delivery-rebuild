package com.epam.star.dao.hibernateH2Dao.impl;

import com.epam.star.dao.hibernateH2Dao.ImageDao;
import com.epam.star.entity.Image;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;

public class H2ImageDao extends GenericJpaDao<Image, Integer> implements ImageDao {
    private static final Logger LOGGER = LoggerFactory.getLogger(H2ImageDao.class);

    public H2ImageDao(EntityManager entityManager, Class<Image> persistentClass) {
        super(entityManager, persistentClass);
    }


    @Override
    public Image findByFileName(String fileName) {
        Assert.notNull(fileName);

        Image image = null;

        Query query = getEntityManager().createQuery("select e from "
                + getPersistentClass().getSimpleName()
                + " e where filename = :fileName")
                .setParameter("fileName", fileName);

        try{
            image = (Image) query.getSingleResult();
        }catch (NoResultException e){}

        return image;
    }

}
