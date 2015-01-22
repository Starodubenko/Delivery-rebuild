package com.epam.star.dao.hibernateH2Dao;

import com.epam.star.entity.Client;

public interface ClientDao extends GenericDao<Client, Integer> {

    boolean checkAvailble(String login);

    Client findByCredentials(String login, String password);
}


