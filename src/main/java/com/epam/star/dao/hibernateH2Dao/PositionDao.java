package com.epam.star.dao.hibernateH2Dao;

import com.epam.star.entity.Position;

public interface PositionDao extends GenericDao<Position, Integer> {

    Position findByPositionName(String positionName);

}
