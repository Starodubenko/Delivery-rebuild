package com.epam.star.dao.hibernateH2Dao.impl;

import com.epam.star.dao.hibernateH2Dao.GenericDao;
import com.epam.star.entity.AbstractEntity;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.io.Serializable;
import java.util.List;

@Transactional
public abstract class GenericJpaDao<T extends AbstractEntity, ID extends Serializable> implements GenericDao<T, ID> {

    private Class<T> persistentClass;
    private EntityManager entityManager;

    public GenericJpaDao(EntityManager entityManager, Class<T> persistentClass) {
        this.entityManager = entityManager;
        this.persistentClass = persistentClass;
    }

    public EntityManager getEntityManager() {
        return entityManager;
    }

    @PersistenceContext
    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public Class<T> getPersistentClass() {
        return persistentClass;
    }

    @Override
    public T save(T entity) {
        getEntityManager().persist(entity);
        return entity;
    }

    @Override
    public T update(T entity) {
        T mergedEntity = getEntityManager().merge(entity);
        return mergedEntity;
    }


    @Override
    public void delete(T entity) {
        if(AbstractEntity.class.isAssignableFrom(persistentClass)){
            getEntityManager().remove(
                    getEntityManager().getReference(
                            entity.getClass(),entity.getId()));
        }else{
            entity = getEntityManager().merge(entity);
            getEntityManager().remove(entity);
        }
    }

    @Transactional(readOnly = true)
    public T findById(ID id) {
        return getEntityManager().find(getPersistentClass(), id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<T> findAll() {
        return getEntityManager()
                .createQuery("select t from " + getPersistentClass().getSimpleName() + " t")
                .getResultList();
    }


//    public void updateEntity(Period period) {
//        Session session = null;
//        try {
//            session = HibernateUtil.getSessionFactory().openSession();
//            session.beginTransaction();
//            session.update(period);
//            session.getTransaction().commit();
//        } catch (Exception e) {
//            JOptionPane.showMessageDialog(null, e.getMessage(), "Ошибка I/O", JOptionPane.OK_OPTION);
//        } finally {
//            if (session != null && session.isOpen()) {
//                session.close();
//            }
//        }
//    }

//    static class QueryBuilder extends GenericJpaDao<AbstractEntity, Integer>{
//
//        private StringBuilder requiredFields = new StringBuilder();
//
//        public QueryBuilder(Class<AbstractEntity> persistentClass) {
//            super(persistentClass);
//
//            CriteriaBuilder criteriaBuilder = getEntityManager().getCriteriaBuilder();
//            CriteriaQuery<AbstractEntity> query = criteriaBuilder.createQuery(getPersistentClass());
//        }
//
//        public QueryBuilder setLogin(String login){
//
//            requiredFields.append(" firstName = " + login);
//
//            return this;
//        }
//
//
//        public List executeQuery(){
//            return getEntityManager().createQuery("select e from Client e " + requiredFields.toString())
//                    .getResultList();
//        }
//
//        private void connectionWord(){
//            if (requiredFields.length() < 1){
//                requiredFields.append("where");
//            } else {
//                requiredFields.append(" and");
//            }
//        }
//    }
}
