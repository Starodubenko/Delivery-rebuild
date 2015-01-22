package com.epam.star.dao.hibernateH2Dao;

import com.epam.star.entity.Status;

public interface StatusDao extends GenericDao<Status, Integer> {
    Status findByStatusName(String statusName);
}
