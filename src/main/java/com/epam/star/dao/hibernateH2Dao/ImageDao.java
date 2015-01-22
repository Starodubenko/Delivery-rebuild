package com.epam.star.dao.hibernateH2Dao;

import com.epam.star.entity.Image;

public interface ImageDao extends GenericDao<Image, Integer>{

    Image findByFileName(String fileName);

}
