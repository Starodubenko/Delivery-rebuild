package com.epam.star.dao.hibernateH2Dao;

import com.epam.star.entity.StatusPayCard;

public interface PayCartStatusDao extends GenericDao<StatusPayCard, Integer> {

    StatusPayCard findByStatusName(String statusName);
}
