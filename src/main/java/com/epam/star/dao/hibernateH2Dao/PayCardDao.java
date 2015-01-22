package com.epam.star.dao.hibernateH2Dao;

import com.epam.star.entity.PayCard;
import com.epam.star.entity.StatusPayCard;

import java.util.List;

public interface PayCardDao extends GenericDao<PayCard, Integer> {

    PayCard findBySerialNumber(String serialNumber);
    PayCard findBySecretNumber(String secretNumber);
    List<PayCard> findByBalance(Integer balance);
    List<PayCard> findByStatus(StatusPayCard status);
}
