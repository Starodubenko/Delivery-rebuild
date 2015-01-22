package com.epam.star.dao.hibernateH2Dao;

import com.epam.star.entity.Contact;

public interface ContactDao extends GenericDao<Contact, Integer> {

    Contact findByTelephone(String telephone);
    Contact findByOwner(String owner);
}
