package com.epam.star.dao.hibernateH2Dao;

import com.epam.star.entity.Discount;

public interface DiscountDao extends GenericDao<Discount, Integer> {

    Discount findByName(String name);
}


